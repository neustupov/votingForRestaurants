package ru.neustupov.votingForRestaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neustupov.votingForRestaurants.model.Restaurant;
import ru.neustupov.votingForRestaurants.repository.RestaurantRepository;
import ru.neustupov.votingForRestaurants.util.exception.NotFoundException;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository){
        this.repository = repository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return null;
    }

    @Override
    public void delete(int id) throws NotFoundException {

    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public void update(Restaurant restaurant) {

    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }

    @Override
    public Restaurant getWithMenus(int id) {
        return null;
    }

    @Override
    public Restaurant getWithVotes(int id) {
        return null;
    }
}
