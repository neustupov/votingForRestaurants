package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.neustupov.votingForRestaurants.model.Meal;
import ru.neustupov.votingForRestaurants.repository.MealRepository;

import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository{

    @Autowired
    private CrudMealRepository crudMealRepository;

    @Transactional
    @Override
    public Meal save(Meal meal, int menuId) {
        return crudMealRepository.save(meal);
    }

    @Transactional
    @Override
    public boolean delete(int id, int menuId) {
        return crudMealRepository.delete(id, menuId) != 0;
    }

    @Override
    public Meal get(int id, int menuId) {
        return crudMealRepository.get(id, menuId).orElse(null);
    }

    @Override
    public List<Meal> getAll(int menuId) {
        return crudMealRepository.getAll(menuId);
    }

    @Override
    public Meal getWithMenu(int id) {
        return crudMealRepository.getWithMenu(id);
    }
}
