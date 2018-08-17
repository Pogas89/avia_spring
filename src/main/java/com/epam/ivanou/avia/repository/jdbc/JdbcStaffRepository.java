package com.epam.ivanou.avia.repository.jdbc;

import com.epam.ivanou.avia.model.Department;
import com.epam.ivanou.avia.model.Staff;
import com.epam.ivanou.avia.repository.StaffRepository;
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
public class JdbcStaffRepository implements StaffRepository {
    private static final RowMapper<Staff> ROW_MAPPER = new RowMapper<Staff>() {
        @Nullable
        @Override
        public Staff mapRow(ResultSet resultSet, int i) throws SQLException {
            int k=1;
            return new Staff(resultSet.getInt(k++),
                    resultSet.getString(k++),
                    resultSet.getString(k++),
                    Department.values()[resultSet.getInt(k)]);
        }
    };

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcStaffRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                               DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
        .withTableName("staffs").usingGeneratedKeyColumns("id");
    }

    @Override
    public Staff save(Staff staff) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id",staff.getId())
                .addValue("firstname",staff.getFirstname())
                .addValue("lastname", staff.getLastname())
                .addValue("department", staff.getDepartment().ordinal());
        if (staff.isNew()){
            Number numKey = simpleJdbcInsert.executeAndReturnKey(map);
            staff.setId(numKey.intValue());
        } else {
            namedParameterJdbcTemplate.update("UPDATE staffs SET firstname=:firstname, lastname=:lastname, " +
                    "department=:department WHERE id=:id;", map);
        }
        return staff;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM staffs WHERE id=?;",id)!=0;
    }

    @Override
    public Staff get(int id) {
        return jdbcTemplate.queryForObject("select id, firstname, lastname, department " +
                "FROM staffs where id=?;",ROW_MAPPER,id);
    }

    @Override
    public Staff getByLastname(String lastname) {
        return jdbcTemplate.queryForObject("select id, firstname, lastname, department " +
                "FROM staffs where lastname=?;",ROW_MAPPER,lastname);
    }

    @Override
    public List<Staff> getAll() {
        return jdbcTemplate.query("select id, firstname, lastname, department " +
                "FROM staffs ORDER BY lastname, firstname",ROW_MAPPER);
    }
}
