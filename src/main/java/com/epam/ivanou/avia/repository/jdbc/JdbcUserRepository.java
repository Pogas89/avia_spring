package com.epam.ivanou.avia.repository.jdbc;

import com.epam.ivanou.avia.model.User;
import com.epam.ivanou.avia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcUserRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("firstname", user.getFirstname())
                .addValue("lastname", user.getLastname())
                .addValue("enabled", user.isEnabled());
        if (user.isNew()) {
            Number newKey = simpleJdbcInsert.executeAndReturnKey(map);
            user.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update("UPDATE users SET email=:email, password=:password, " +
                    "firstname=:firstname, lastname=:lastname, enabled:=enabled WHERE id=:id;", map);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        return jdbcTemplate.queryForObject("SELECT users.id, users.email, users.password, users.firstname," +
                " users.lastname, users.enabled FROM users WHERE users.id =?;", ROW_MAPPER, id);
    }

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT users.id, users.email, users.password, users.firstname," +
                " users.lastname, users.enabled FROM users WHERE users.email =?;", ROW_MAPPER, email);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT users.id, users.email, users.password, users.firstname," +
                " users.lastname, users.enabled FROM users ORDER BY email", ROW_MAPPER);
    }
}