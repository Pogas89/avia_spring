package com.epam.ivanou.avia;

import com.epam.ivanou.avia.model.Flight;
import com.epam.ivanou.avia.model.FlightStatus;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FlightTestData {
    public static Integer FLIGHT1_ID = 1000;
    public static Integer FLIGHT2_ID = 1001;

    public static Flight FLIGHT1 = new Flight(FLIGHT1_ID,"79339", "Myanmar", "Dominica",
            LocalDateTime.of (2016, 1, 18,18, 12, 45), FlightStatus.OPENED, null);
    public static Flight FLIGHT2 = new Flight(FLIGHT2_ID,"88170","Norfolk Island","Brazil",
            LocalDateTime.of(2017,9,18,00,07,24), FlightStatus.OPENED,null);

    public static void assertMatch(Flight actual, Flight expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected,"datetime","crew");
    }

    public static void assertMatch(Iterable<Flight> actual, Iterable<Flight> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("datetime","crew").isEqualTo(expected);
    }
    public static void assertMatch(Iterable<Flight> actual, Flight... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }
}
