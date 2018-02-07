package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neustupov.votingForRestaurants.model.Restaurant;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer>{
}
