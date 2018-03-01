package ru.neustupov.votingForRestaurants.repository.mockRepository;

import ru.neustupov.votingForRestaurants.model.Meal;
import ru.neustupov.votingForRestaurants.repository.MealRepository;
import ru.neustupov.votingForRestaurants.util.MockUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.neustupov.votingForRestaurants.repository.mockRepository.InMemoryUserRepository.USER_ID;

public class InMemoryMealRepositoryImpl implements MealRepository{

    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Meal save(Meal user) {
        return null;
    }

    @Override
    public boolean delete(int id, int menuId) {
        return false;
    }

    @Override
    public Meal get(int id) {
        return null;
    }

    @Override
    public List<Meal> getAll() {
        return null;
    }

    @Override
    public List<Meal> getAllByIdMenu(int idRest) {
        return null;
    }

    @Override
    public Meal getWithMenu(int id) {
        return null;
    }
}
