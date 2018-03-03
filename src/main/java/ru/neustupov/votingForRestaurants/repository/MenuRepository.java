package ru.neustupov.votingForRestaurants.repository;

import ru.neustupov.votingForRestaurants.model.Menu;

import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu, int restId);

    // false if not found
    boolean delete(int id, int restId);

    // null if not found
    Menu get(int id, int restId);

    List<Menu> getAll(int restId);

    default Menu getWithRestaurant(int id) {
        throw new UnsupportedOperationException();
    }

    default Menu getWithMeals(int id) {
        throw new UnsupportedOperationException();
    }

    default Menu getWithRestaurantAndMeals(int id) {
        throw new UnsupportedOperationException();
    }
}
