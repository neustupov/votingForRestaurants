package ru.neustupov.votingForRestaurants.service;

import ru.neustupov.votingForRestaurants.model.Menu;
import ru.neustupov.votingForRestaurants.util.exception.NotFoundException;

import java.util.List;

public interface MenuService {

    Menu create(Menu menu, int restId);

    void delete(int id, int restId) throws NotFoundException;

    Menu get(int id, int restId) throws NotFoundException;

    void update(Menu menu, int restId);

    List<Menu> getAll(int restId);

    Menu getWithMeals(int id);

    Menu getWithRestaurant(int id);

    Menu getWitMealsAndRestaurant(int id);
}
