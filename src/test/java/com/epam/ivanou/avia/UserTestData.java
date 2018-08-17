package com.epam.ivanou.avia;

import com.epam.ivanou.avia.model.Role;
import com.epam.ivanou.avia.model.User;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static Integer ADMIN_ID = 1;
    public static Integer USER_ID = 2;
    public static User ADMIN = new User(ADMIN_ID, "admin@gmail.com",
            "admin", "Dmitry", "Ivanov", Role.ROLE_ADMIN);
    public static User USER = new User(USER_ID, "user@gmail.com",
            "user", "Ostap", "Bender", Role.ROLE_DISPETCHER);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "role");
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("role").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }
}
