package com.epam.ivanou.avia.service.jdbc;

import com.epam.ivanou.avia.service.FlightServiceTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles({"postgres", "jdbc"})
public class JdbcFlightServiceTest extends FlightServiceTest {
}