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
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {

    private static final RowMapper<User> ROW_MAPPER = new RowMapper<User>() {
        @Nullable
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            int k = 1;
            User user = new User(
                    rs.getInt(k++),
                    rs.getString(k++),
                    rs.getString(k++),
                    rs.getString(k++),
                    rs.getString(k++),
                    Role.valueOf(rs.getString(k++))
            );
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
                .addValue("us_email", user.getEmail())
                .addValue("us_password", user.getPassword())
                .addValue("us_Fname", user.getFirstName())
                .addValue("us_Lname", user.getLastName());
        if (user.isNew()) {
            Number newKey = simpleJdbcInsert.executeAndReturnKey(map);
            user.setId(newKey.intValue());
            jdbcTemplate.update("INSERT INTO role (user_id, role) VALUES (?,?)",user.getId(),user.getRole().toString());
        } else {
            namedParameterJdbcTemplate.update("UPDATE user SET us_password=:us_password, us_Fname=:us_Fname, us_Lname=:us_Lname," +
                    " us_email=:us_email WHERE user_id=:user_id;", map);
            jdbcTemplate.update("UPDATE role  SET  role=? WHERE user_id=?;",user.getRole().toString(), user.getId());
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM user WHERE user_id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        return jdbcTemplate.queryForObject("SELECT user.user_id, us_email, us_password,us_Fname,us_Lname," +
                "role.role FROM user INNER JOIN role ON user.user_id = role.user_id WHERE user.user_id =?;", ROW_MAPPER, id);
    }

    @Override
    public User getByLogin(String email) {
        return jdbcTemplate.queryForObject("SELECT user.user_id, us_email, us_password,us_Fname,us_Lname," +
                "role.role FROM user INNER JOIN role ON user.user_id = role.user_id WHERE us_email =?;", ROW_MAPPER, email);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT user.user_id, us_email, us_password,us_Fname,us_Lname," +
                "role.role FROM user INNER JOIN role ON user.user_id = role.user_id ORDER BY us_email", ROW_MAPPER);
    }
}
