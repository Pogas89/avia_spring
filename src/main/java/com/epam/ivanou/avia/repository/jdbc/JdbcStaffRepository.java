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
            Staff staff = new Staff(resultSet.getInt(k++),
                    resultSet.getString(k++),
                    resultSet.getString(k++),
                    Department.values()[resultSet.getInt(k)]);
            return staff;
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
        .withTableName("staff").usingGeneratedKeyColumns("st_id");
    }

    @Override
    public Staff save(Staff staff) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("st_id",staff.getId())
                .addValue("st_Fname",staff.getFirstName())
                .addValue("st_Lname", staff.getLastName())
                .addValue("dep_id", staff.getDepartment().ordinal());
        if (staff.isNew()){
            Number numKey = simpleJdbcInsert.executeAndReturnKey(map);
            staff.setId(numKey.intValue());
        } else {
            namedParameterJdbcTemplate.update("UPDATE staff SET st_Fname=:st_Fname, st_Lname=:st_Lname," +
                    "dep_id=:dep_id WHERE st_id=:st_id;", map);
        }
        return staff;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM staff WHERE st_id=?;",id)!=0;
    }

    @Override
    public Staff get(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM staff WHERE st_id=?;",ROW_MAPPER,id);
    }

    @Override
    public Staff getByLastname(String lastname) {
        return jdbcTemplate.queryForObject("SELECT  * FROM staff WHERE st_Lname=?;",ROW_MAPPER,lastname);
    }

    @Override
    public List<Staff> getAll() {
        return jdbcTemplate.query("SELECT  * FROM staff ORDER BY st_Lname, st_Fname",ROW_MAPPER);
    }
}
