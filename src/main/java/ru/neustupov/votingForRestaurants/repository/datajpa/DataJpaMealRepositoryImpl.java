package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.neustupov.votingForRestaurants.model.Meal;
import ru.neustupov.votingForRestaurants.repository.MealRepository;

import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository{

    @Autowired
    private CrudMealRepository crudMealRepository;

    @Override
    public Meal save(Meal meal) {
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int menuId) {
        return crudMealRepository.delete(id, menuId) != 0;
    }

    @Override
    public Meal get(int id) {
        return crudMealRepository.findById(id).orElse(null);
    }

    @Override
    public List<Meal> getAll() {
        return crudMealRepository.findAll();
    }

    @Override
    public Meal getWithMenu(int id) {
        return crudMealRepository.getWithMenu(id);
    }
}
