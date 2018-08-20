package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.CrewTestData;
import com.epam.ivanou.avia.model.Flight;
import com.epam.ivanou.avia.model.FlightStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static com.epam.ivanou.avia.FlightTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@ActiveProfiles("postgres")
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populatePostgresDb.sql", config = @SqlConfig(encoding = "UTF-8"))
public class FlightServiceTest {

    @Autowired
    private FlightService service;

    @Test
    public void create() throws Exception {
        Flight flight = service.create(new Flight(null, "11111", "AAAA", "AAAA", LocalDateTime.of(2016, 10, 18,12, 12, 45), FlightStatus.OPENED, null));
        assertMatch(service.getAll(), FLIGHT1, flight, FLIGHT2);
    }

    @Test
    public void update() throws Exception {
        Flight newFlight = new Flight(FLIGHT1);
        newFlight.setDeparture("Asdasd");
        newFlight.setDestination("sadsAsdasd");
        newFlight.setName("1dasda1");
        newFlight.setDatetime(LocalDateTime.of(2010, 1, 3,10, 10, 10));
        newFlight.setStatus(FlightStatus.CLOSED);
        newFlight.setCrew(CrewTestData.CREW2);
        service.update(newFlight);
        assertMatch(newFlight, service.get(FLIGHT1_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(FLIGHT1_ID);
        assertMatch(service.getAll(), FLIGHT2);
    }

    @Test(expected = Exception.class)
    public void getNotFound() throws Exception {
        service.get(0);
    }

    @Test
    public void get() throws Exception {
        Flight flight = service.get(FLIGHT1_ID);
        assertMatch(flight, FLIGHT1);
    }

    @Test
    public void getAll() throws Exception {
        List<Flight> flights = service.getAll();
        assertMatch(flights, FLIGHT1, FLIGHT2);
    }

}