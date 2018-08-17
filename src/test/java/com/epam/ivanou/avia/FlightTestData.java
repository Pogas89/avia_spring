package com.epam.ivanou.avia;

import com.epam.ivanou.avia.model.Flight;
import com.epam.ivanou.avia.model.FlightStatus;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FlightTestData {
    public static Integer FLIGHT1_ID = 1;
    public static Integer FLIGHT2_ID = 2;

    public static Flight FLIGHT1 = new Flight(FLIGHT1_ID,"79339", "Myanmar", "Dominica",
            LocalDateTime.of (2016, 1, 16,18, 12, 45), FlightStatus.OPENED, null);
    public static Flight FLIGHT2 = new Flight(FLIGHT2_ID,"88170","Norfolk Island","Brazil",
            LocalDateTime.of(2018,9,17,00,07,24), FlightStatus.OPENED,null);

    public static void assertMatch(Flight actual, Flight expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Flight> actual, Iterable<Flight> expected) {
        assertThat(actual).isEqualTo(expected);
    }
    public static void assertMatch(Iterable<Flight> actual, Flight... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }
}
