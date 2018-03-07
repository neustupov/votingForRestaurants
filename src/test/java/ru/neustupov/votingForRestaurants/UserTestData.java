package ru.neustupov.votingForRestaurants;

import ru.neustupov.votingForRestaurants.model.Role;
import ru.neustupov.votingForRestaurants.model.User;

import java.time.Instant;
import java.util.Date;
import java.util.EnumSet;

public class UserTestData {

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    public static final User USER = new User(USER_ID, "User", "123", Date.from(Instant.now()),  EnumSet.of(Role.ROLE_USER));
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "456", Date.from(Instant.now()), EnumSet.allOf(Role.class));
}
