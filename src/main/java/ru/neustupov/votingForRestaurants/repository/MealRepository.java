package ru.neustupov.votingForRestaurants.repository;

import ru.neustupov.votingForRestaurants.model.Meal;

import java.util.List;

public interface MealRepository {

    Meal save(Meal user);

    // false if not found
    boolean delete(int id, int menuId);

    // null if not found
    Meal get(int id);

    List<Meal> getAll();

    default Meal getWithMenu(int id){
        throw new UnsupportedOperationException();
    }
}
