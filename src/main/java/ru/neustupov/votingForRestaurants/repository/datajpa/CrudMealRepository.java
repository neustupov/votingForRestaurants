package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neustupov.votingForRestaurants.model.Meal;

public interface CrudMealRepository extends JpaRepository<Meal, Integer>{
}
