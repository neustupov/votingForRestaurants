package ru.neustupov.votingForRestaurants.service;

import ru.neustupov.votingForRestaurants.model.Meal;
import ru.neustupov.votingForRestaurants.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal create(Meal meal);

    void delete(int id) throws NotFoundException;

    Meal get(int id) throws NotFoundException;

    void update(Meal meal);

    List<Meal> getAll();

    Meal getWithMenu(int id);
}
