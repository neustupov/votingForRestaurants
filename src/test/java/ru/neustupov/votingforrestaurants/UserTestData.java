package ru.neustupov.votingforrestaurants;

import ru.neustupov.votingforrestaurants.model.Role;
import ru.neustupov.votingforrestaurants.model.User;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.neustupov.votingforrestaurants.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "password", Date.from(Instant.now()),  EnumSet.of(Role.ROLE_USER));
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin", Date.from(Instant.now()), EnumSet.of(Role.ROLE_ADMIN));

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles", "votes");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "votes").isEqualTo(expected);
    }
}
