package ru.neustupov.votingForRestaurants.service;

import ru.neustupov.votingForRestaurants.model.Menu;
import ru.neustupov.votingForRestaurants.util.exception.NotFoundException;

import java.util.List;

public interface MenuService {

    Menu create(Menu menu);

    void delete(int id, int restId) throws NotFoundException;

    Menu get(int id) throws NotFoundException;

    void update(Menu menu);

    List<Menu> getAll();

    List<Menu> getAllByIdRest(int idRest);

    Menu getWithMeals(int id);

    Menu getWithRestaurant(int id);

    Menu getWitMealsAndRestaurant(int id);
}
