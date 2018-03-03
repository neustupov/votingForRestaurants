package ru.neustupov.votingForRestaurants.repository.mockRepository;

import ru.neustupov.votingForRestaurants.model.Restaurant;
import ru.neustupov.votingForRestaurants.repository.RestaurantRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryRestaurantRepositoryImpl implements RestaurantRepository{

    private Map<Integer, Restaurant> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            restaurant.setId(counter.incrementAndGet());
            repository.put(restaurant.getId(), restaurant);
            return restaurant;
        }
        return repository.computeIfPresent(restaurant.getId(), (id, oldRestaurant) -> restaurant);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Restaurant get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.values().stream()
                .sorted(Comparator.comparing(Restaurant::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Restaurant getWithVotes(int id) {
        return null;
    }

    @Override
    public Restaurant getWithMenus(int id) {
        return null;
    }

    @Override
    public Restaurant getWithVotesAndMenus(int id) {
        return null;
    }
}
