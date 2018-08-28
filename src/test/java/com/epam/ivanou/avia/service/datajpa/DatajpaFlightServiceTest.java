package com.epam.ivanou.avia.service.datajpa;

import com.epam.ivanou.avia.service.FlightServiceTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles({"postgres", "datajpa"})
public class DatajpaFlightServiceTest extends FlightServiceTest {
}