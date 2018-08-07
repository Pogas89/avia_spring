package com.epam.ivanou.avia;

import com.epam.ivanou.avia.model.Role;
import com.epam.ivanou.avia.model.User;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static Integer ADMIN_ID = 1000;
    public static Integer USER_ID = 1001;
    public static User ADMIN = new User(1000, "admin", "admin", "Dmitry", "Ivanov", "poshta@gmail.com", Role.values()[0]);
    public static User USER = new User(1001, "user1", "user", "Ostap", "Bender", "bender@gmail.com", Role.values()[1]);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "roles");
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("roles").isEqualTo(expected);
    }
    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }
}
