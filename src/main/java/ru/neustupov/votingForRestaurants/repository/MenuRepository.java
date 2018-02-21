package ru.neustupov.votingForRestaurants.repository;

import ru.neustupov.votingForRestaurants.model.Menu;

import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu);

    // false if not found
    boolean delete(int id, int restId);

    // null if not found
    Menu get(int id);

    List<Menu> getAllByRestId(int restId);

    List<Menu> getAll();

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
