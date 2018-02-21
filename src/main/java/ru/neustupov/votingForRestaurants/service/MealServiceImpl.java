package ru.neustupov.votingForRestaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.neustupov.votingForRestaurants.model.Meal;
import ru.neustupov.votingForRestaurants.repository.MealRepository;
import ru.neustupov.votingForRestaurants.util.exception.NotFoundException;

import java.util.List;

import static ru.neustupov.votingForRestaurants.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService{

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository){
        this.repository = repository;
    }

    @Override
    public Meal create(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal);
    }

    @Override
    public void delete(int id, int menuId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, menuId), id);
    }

    @Override
    public Meal get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void update(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

    @Override
    public List<Meal> getAll() {
        return repository.getAll();
    }

    @Override
    public Meal getWithMenu(int id) {
        return checkNotFoundWithId(repository.getWithMenu(id), id);
    }
}
