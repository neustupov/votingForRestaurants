package ru.neustupov.votingForRestaurants.util;

import ru.neustupov.votingForRestaurants.model.Meal;
import ru.neustupov.votingForRestaurants.model.Vote;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MockUtil {

    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(0, "soup", 0, 100),
            new Meal(1, "pasta", 0, 200),
            new Meal(2, "juice", 0, 50)
    );

    public static final List<Vote> VOTES = Arrays.asList(
            new Vote(0, LocalDateTime.of(2015, Month.MAY, 30, 10, 0)),
            new Vote(1, LocalDateTime.of(2015, Month.JULY, 10, 11, 0))
    );
}
