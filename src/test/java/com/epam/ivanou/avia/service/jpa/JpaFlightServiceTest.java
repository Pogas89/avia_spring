package com.epam.ivanou.avia.service.jpa;

import com.epam.ivanou.avia.service.FlightServiceTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles({"postgres", "jpa"})
public class JpaFlightServiceTest extends FlightServiceTest {
}