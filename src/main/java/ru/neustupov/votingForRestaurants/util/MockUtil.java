package ru.neustupov.votingForRestaurants.util;

import ru.neustupov.votingForRestaurants.model.Meal;
import ru.neustupov.votingForRestaurants.model.Menu;
import ru.neustupov.votingForRestaurants.model.Restaurant;
import ru.neustupov.votingForRestaurants.model.Vote;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MockUtil {

    private static AtomicInteger counterMeals = new AtomicInteger(0);
    private static AtomicInteger counterVotes = new AtomicInteger(0);
    private static AtomicInteger counterRestaurants = new AtomicInteger(0);
    private static AtomicInteger counterMenus = new AtomicInteger(0);

    public static List<Meal> MEALS = Arrays.asList(
            new Meal(counterMeals.incrementAndGet(), "soup", 1, 100),
            new Meal(counterMeals.incrementAndGet(), "pasta", 2, 200),
            new Meal(counterMeals.incrementAndGet(), "juice", 3, 50)
    );


    public static List<Vote> VOTES = new ArrayList<>();

    static {
        VOTES.add(new Vote(counterVotes.incrementAndGet(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0)));
        VOTES.add(new Vote(counterVotes.incrementAndGet(), LocalDateTime.of(2015, Month.JULY, 10, 11, 0)));
    }

    public static List<Restaurant> RESTAURANTS = Arrays.asList(
            new Restaurant(counterRestaurants.incrementAndGet(), "Topaz"),
            new Restaurant(counterRestaurants.incrementAndGet(), "Gruzia"),
            new Restaurant(counterRestaurants.incrementAndGet(), "Afonja")
    );

    public static List<Menu> MENUS = Arrays.asList(
            new Menu(counterMenus.incrementAndGet(), 1, LocalDateTime.of(2015, Month.MAY, 29, 10, 0)),
            new Menu(counterMenus.incrementAndGet(), 1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0)),
            new Menu(counterMenus.incrementAndGet(), 2, LocalDateTime.of(2015, Month.MAY, 29, 10, 0)),
            new Menu(counterMenus.incrementAndGet(), 3, LocalDateTime.of(2015, Month.MAY, 29, 10, 0))
    );

    public static Integer getCounterForVote() {
        return counterVotes.incrementAndGet();
    }
}
