package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.neustupov.votingForRestaurants.repository.RestaurantRepository;

@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository{

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;
}
