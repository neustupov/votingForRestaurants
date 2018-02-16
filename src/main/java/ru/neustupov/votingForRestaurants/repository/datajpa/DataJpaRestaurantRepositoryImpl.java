package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.neustupov.votingForRestaurants.model.Restaurant;
import ru.neustupov.votingForRestaurants.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository{

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll();
    }

    @Override
    public Restaurant getWithVotes(int id) {
        return crudRestaurantRepository.getWithVotes(id);
    }

    @Override
    public Restaurant getWithMenus(int id) {
        return crudRestaurantRepository.getWithMenus(id);
    }

    @Override
    public Restaurant getWithVotesAndMenus(int id) {
        return crudRestaurantRepository.getWithVotesAndMenus(id);
    }
}
