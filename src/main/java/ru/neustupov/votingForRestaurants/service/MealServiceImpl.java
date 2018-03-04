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
    public Meal create(Meal meal, int menuId) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal, menuId);
    }

    @Override
    public void delete(int id, int menuId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, menuId), id);
    }

    @Override
    public Meal get(int id, int menuId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, menuId), id);
    }

    @Override
    public void update(Meal meal, int menuId) {
        Assert.notNull(meal, "meal must not be null");
        checkNotFoundWithId(repository.save(meal, menuId), meal.getId());
    }

    @Override
    public List<Meal> getAll(int menuId) {
        return repository.getAll(menuId);
    }

    @Override
    public Meal getWithMenu(int id) {
        return checkNotFoundWithId(repository.getWithMenu(id), id);
    }
}
