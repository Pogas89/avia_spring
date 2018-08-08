package com.epam.ivanou.avia;

import com.epam.ivanou.avia.model.Department;
import com.epam.ivanou.avia.model.Staff;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class StaffTestData {
    public static final Integer STAFF1_ID = 1000;
    public static final Integer STAFF2_ID = 1001;

    public static final Staff STAFF1 = new Staff(STAFF1_ID, "Alexis", "Roy", Department.values()[0]);
    public static final Staff STAFF2 = new Staff(STAFF2_ID, "Kylan", "Knapp", Department.values()[2]);

    public static void assertMatch(Staff actual, Staff expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Staff> actual, Iterable<Staff> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Staff> actual, Staff... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }
}
