package ru.neustupov.votingForRestaurants.service;

import ru.neustupov.votingForRestaurants.model.Meal;
import ru.neustupov.votingForRestaurants.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal create(Meal meal, int menuId);

    void delete(int id, int menuId) throws NotFoundException;

    Meal get(int id, int menuId) throws NotFoundException;

    void update(Meal meal, int menuId);

    List<Meal> getAll(int menuId);

    Meal getWithMenu(int id);
}
