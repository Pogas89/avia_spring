package com.epam.ivanou.avia;

import com.epam.ivanou.avia.model.Crew;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CrewTestData {
    public static Integer CREW_ID = 1000;
    public static Integer CREW2_ID = 1001;
    public static Crew CREW = new Crew(CREW_ID, "Crew1", UserTestData.ADMIN);
    public static Crew CREW2 = new Crew(CREW2_ID, "Crew2", UserTestData.ADMIN);

    public static void assertMatch(Crew actual, Crew expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Crew> actual, Iterable<Crew> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Crew> actual, Crew... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }
}
