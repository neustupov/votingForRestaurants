package ru.neustupov.votingforrestaurants.util;

import ru.neustupov.votingforrestaurants.model.Role;
import ru.neustupov.votingforrestaurants.model.User;
import ru.neustupov.votingforrestaurants.to.UserTo;

import java.util.EnumSet;

public class UserUtil {

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), EnumSet.of(Role.ROLE_USER));
    }
}
