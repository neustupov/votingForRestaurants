package ru.neustupov.votingForRestaurants.repository;

import ru.neustupov.votingForRestaurants.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant user);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();

    default Restaurant getWithVotes(int id){
        throw new UnsupportedOperationException();
    }

    default Restaurant getWithMenus(int id){
        throw new UnsupportedOperationException();
    }
}
