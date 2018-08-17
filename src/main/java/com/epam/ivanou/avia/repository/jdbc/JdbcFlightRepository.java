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
                    FlightStatus.values()[resultSet.getInt(k++)],
                    new Crew());
                    flight.getCrew().setId(resultSet.getInt(k));
            return flight;
        }
    };

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcFlightRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("flights")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Flight save(Flight flight) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", flight.getId())
                .addValue("name", flight.getName())
                .addValue("departure", flight.getDeparture())
                .addValue("destination", flight.getDestination())
                .addValue("datetime", flight.getDatetime())
                .addValue("status", flight.getStatus().ordinal())
                .addValue("crew_id", flight.getCrew()!=null?flight.getCrew().getId():null);

        if (flight.isNew()) {
            Number newKey = simpleJdbcInsert.executeAndReturnKey(map);
            flight.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update("UPDATE flights SET name=:name," +
                    "departure=:departure, destination=:destination, datetime=:datetime," +
                    "status=:status, crew_id=:crew_id WHERE id=:id;", map);
        }
        return flight;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM flights WHERE id=?;", id) != 0;
    }

    @Override
    public Flight get(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, departure, destination, " +
                "datetime, status, crew_id FROM flights WHERE id=?;", ROW_MAPPER, id);
    }

    @Override
    public List<Flight> getAll() {
        return jdbcTemplate.query("SELECT id, name, departure, destination, " +
                "datetime, status, crew_id FROM flights ORDER BY datetime;", ROW_MAPPER);
    }
}
