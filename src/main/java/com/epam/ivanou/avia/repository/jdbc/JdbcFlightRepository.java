package com.epam.ivanou.avia.repository.jdbc;

import com.epam.ivanou.avia.model.Crew;
import com.epam.ivanou.avia.model.Flight;
import com.epam.ivanou.avia.model.FlightStatus;
import com.epam.ivanou.avia.repository.FlightRepository;
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
public class JdbcFlightRepository implements FlightRepository {
    private static final RowMapper<Flight> ROW_MAPPER = new RowMapper<Flight>() {
        @Nullable
        @Override
        public Flight mapRow(ResultSet resultSet, int i) throws SQLException {
            int k = 1;
            Flight flight = new Flight(
                    resultSet.getInt(k++),
                    resultSet.getString(k++),
                    resultSet.getString(k++),
                    resultSet.getString(k++),
                    resultSet.getTimestamp(k++).toLocalDateTime(),
                    FlightStatus.valueOf(resultSet.getString(k++)),
                    resultSet.getInt(k++));
            return flight;
        }
    };

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcFlightRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("flight")
                .usingGeneratedKeyColumns("fl_id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Flight save(Flight flight) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("fl_id", flight.getId())
                .addValue("fl_name", flight.getName())
                .addValue("fl_departure", flight.getDeparture())
                .addValue("fl_destination", flight.getDestination())
                .addValue("fl_datetime", flight.getDatetime())
                .addValue("fl_stat_id", flight.getStatus().ordinal())
                .addValue("crew_id", flight.getCrew()!=null?flight.getCrew().getId():null);

        if (flight.isNew()) {
            Number newKey = simpleJdbcInsert.executeAndReturnKey(map);
            flight.setId(newKey.intValue());
            jdbcTemplate.update("INSERT INTO flight_status (fl_id, fl_status) VALUES (?,?)",flight.getId(),flight.getStatus().toString());
        } else {
            namedParameterJdbcTemplate.update("UPDATE flight SET fl_name=:fl_name," +
                    "fl_departure=:fl_departure, fl_destination=:fl_destination, fl_datetime=:fl_datetime," +
                    " crew_id=:crew_id WHERE fl_id=:fl_id;", map);
            jdbcTemplate.update("UPDATE flight_status  SET  fl_status=? WHERE fl_id=?;",flight.getStatus().toString(), flight.getId());
        }
        return flight;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM flight WHERE fl_id=?;", id) != 0;
    }

    @Override
    public Flight get(int id) {
        return jdbcTemplate.queryForObject("SELECT flight.fl_id, fl_name, fl_departure,fl_destination," +
                "fl_datetime ,fl_status, crew_id FROM flight inner join flight_status f on " +
                "flight.fl_id = f.fl_id WHERE flight.fl_id=?;", ROW_MAPPER, id);
    }

    @Override
    public List<Flight> getAll() {
        return jdbcTemplate.query("SELECT flight.fl_id, fl_name, fl_departure,fl_destination," +
                "fl_datetime, fl_status, crew_id FROM flight inner join flight_status f " +
                "on flight.fl_id = f.fl_id ORDER BY fl_datetime;", ROW_MAPPER);
    }
}
