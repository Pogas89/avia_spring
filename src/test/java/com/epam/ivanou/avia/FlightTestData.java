package com.epam.ivanou.avia;

import com.epam.ivanou.avia.model.Flight;
import com.epam.ivanou.avia.model.FlightStatus;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FlightTestData {
    public static Integer FLIGHT1_ID = 1000;
    public static Integer FLIGHT2_ID = 1001;

    public static Flight FLIGHT1 = new Flight(FLIGHT1_ID,"79339", "Myanmar", "Dominica", new Date(2016, 1, 18),
            new Time(18, 12, 45), FlightStatus.values()[0], null);
    public static Flight FLIGHT2 = new Flight(FLIGHT2_ID,"88170","Norfolk Island","Brazil",new Date(2017,9,18),
            new Time(00,07,24), FlightStatus.values()[0],null);

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
