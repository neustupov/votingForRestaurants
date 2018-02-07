package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.neustupov.votingForRestaurants.repository.MealRepository;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository{

    @Autowired
    private CrudMealRepository crudMealRepository;
}
