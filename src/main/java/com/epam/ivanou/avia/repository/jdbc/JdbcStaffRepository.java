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
                    Department.valueOf(resultSet.getString(k++)));
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
                .addValue("st_Fname",staff.getFirstname())
                .addValue("st_Lname", staff.getLastname());
        if (staff.isNew()){
            Number numKey = simpleJdbcInsert.usingColumns("st_Fname","st_Lname").executeAndReturnKey(map);
            staff.setId(numKey.intValue());
            jdbcTemplate.update("INSERT INTO department (st_id, dep_name) values (?,?)",staff.getId(),staff.getDepartment().toString());
        } else {
            namedParameterJdbcTemplate.update("UPDATE staff SET st_Fname=:st_Fname, st_Lname=:st_Lname" +
                    " WHERE st_id=:st_id;", map);
            jdbcTemplate.update("UPDATE department set dep_name=? where department.st_id=?;",staff.getDepartment().toString(), staff.getId());
        }
        return staff;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM staff WHERE st_id=?;",id)!=0;
    }

    @Override
    public Staff get(int id) {
        return jdbcTemplate.queryForObject("select staff.st_id,st_Fname,st_Lname, dep_name " +
                "from staff inner join department d on staff.st_id = d.st_id where staff.st_id=?;",ROW_MAPPER,id);
    }

    @Override
    public Staff getByLastname(String lastname) {
        return jdbcTemplate.queryForObject("select staff.st_id,st_Fname,st_Lname, dep_name " +
                "from staff inner join department d on staff.st_id = d.st_id where st_Lname=?;",ROW_MAPPER,lastname);
    }

    @Override
    public List<Staff> getAll() {
        return jdbcTemplate.query("select staff.st_id,st_Fname,st_Lname, dep_name " +
                "from staff inner join department d on staff.st_id = d.st_id ORDER BY st_Lname, st_Fname",ROW_MAPPER);
    }
}
