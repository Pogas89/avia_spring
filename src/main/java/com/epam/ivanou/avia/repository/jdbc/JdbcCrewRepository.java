package com.epam.ivanou.avia.repository.jdbc;

import com.epam.ivanou.avia.model.Crew;
import com.epam.ivanou.avia.model.User;
import com.epam.ivanou.avia.repository.CrewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcCrewRepository implements CrewRepository {
    private static final RowMapper<Crew> ROW_MAPPER = new RowMapper<Crew>() {
        @Nullable
        @Override
        public Crew mapRow(ResultSet resultSet, int i) throws SQLException {
            int k=1;
            Crew crew = new Crew(resultSet.getInt(k++),
                    resultSet.getString(k++),
                    new User());
            crew.getUser().setId(resultSet.getInt(k));
            return crew;
        }
    };

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcCrewRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("crew")
                .usingGeneratedKeyColumns("cr_id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Crew save(Crew crew) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("cr_id",crew.getId())
                .addValue("cr_name", crew.getName())
                .addValue("user_id", crew.getUser().getId());

        if (crew.isNew()){
            Number newKey = simpleJdbcInsert.executeAndReturnKey(map);
            crew.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update("UPDATE  crew SET cr_id=:cr_id, cr_name=:cr_name, user_id=:user_id;",map);
        }
        return crew;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM crew WHERE cr_id=?;",id)!=0;
    }

    @Override
    public Crew get(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM crew WHERE cr_id=?;", ROW_MAPPER,id);
    }

    @Override
    public List<Crew> getAll() {
        return jdbcTemplate.query("SELECT * FROM crew ORDER BY cr_name",ROW_MAPPER);
    }
}
