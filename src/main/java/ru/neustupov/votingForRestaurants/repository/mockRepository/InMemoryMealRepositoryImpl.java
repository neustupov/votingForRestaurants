package ru.neustupov.votingForRestaurants.repository.mockRepository;

import org.springframework.stereotype.Repository;
import ru.neustupov.votingForRestaurants.model.Meal;
import ru.neustupov.votingForRestaurants.repository.MealRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository{

    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new Meal(3, "vine", 300), 3);
        save(new Meal(3, "tea", 10), 3);
        save(new Meal(1, "soup", 100), 1);
        save(new Meal(2, "pasta", 200), 2);
        save(new Meal(2, "juice", 50), 2);
    }

    @Override
    public Meal save(Meal meal, int menuId) {
        Map<Integer, Meal> meals = repository.computeIfAbsent(menuId, ConcurrentHashMap::new);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(), meal);
            return meal;
        }
        return meals.computeIfPresent(meal.getId(), (id, oldmeal) -> meal);
    }

    @Override
    public boolean delete(int id, int menuId) {
        Map<Integer, Meal> meals = repository.get(menuId);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int menuId) {
        Map<Integer, Meal> meals = repository.get(menuId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public List<Meal> getAll(int menuId) {
        return getAllAsStream(menuId).collect(Collectors.toList());
    }

    @Override
    public Meal getWithMenu(int id) {
        return null;
    }

    private Stream<Meal> getAllAsStream(int menuId) {
        Map<Integer, Meal> meals = repository.get(menuId);
        return meals == null ?
                Stream.empty() :
                meals.values().stream()
                        .sorted(Comparator.comparing(Meal::getId));
    }
}
