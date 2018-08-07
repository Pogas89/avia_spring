package com.epam.ivanou.avia.repository.jdbc;

import com.epam.ivanou.avia.model.Role;
import com.epam.ivanou.avia.model.User;
import com.epam.ivanou.avia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {

    private static final RowMapper<User> ROW_MAPPER = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            int k = 1;
            User user = new User(resultSet.getInt(k++),
                    resultSet.getString(k++),
                    resultSet.getString(k++),
                    resultSet.getString(k++),
                    resultSet.getString(k++),
                    resultSet.getString(k++),
                    Role.values()[resultSet.getInt(k)]);
            return user;
        }
    };

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcUserRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("user")
                .usingGeneratedKeyColumns("user_id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("user_id", user.getId())
                .addValue("us_login", user.getLogin())
                .addValue("us_password", user.getPassword())
                .addValue("us_Fname", user.getFirstName())
                .addValue("us_Lname", user.getLastName())
                .addValue("us_email", user.getEmail())
                .addValue("role_id", user.getRoles().ordinal());

        if (user.isNew()) {
            Number newKey = simpleJdbcInsert.executeAndReturnKey(map);
            user.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update("UPDATE user SET us_login=:us_login, us_password=:us_password, us_Fname=:us_Fname, us_Lname=:us_Lname," +
                    " us_email=:us_email, role_id=:role_id WHERE user_id=:user_id;", map);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM user WHERE user_id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        return jdbcTemplate.queryForObject("SELECT user_id, us_login, us_password,us_Fname,us_Lname," +
                "us_email,role_id FROM user WHERE user_id =?;", ROW_MAPPER, id);
    }

    @Override
    public User getByLogin(String login) {
        return jdbcTemplate.queryForObject("SELECT user_id, us_login, us_password,us_Fname,us_Lname, us_email,role_id " +
                "FROM user WHERE us_login =?;", ROW_MAPPER, login);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT user_id, us_login, us_password,us_Fname,us_Lname, us_email,role_id " +
                "FROM user ORDER BY us_login", ROW_MAPPER);
    }
}
