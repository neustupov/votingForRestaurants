package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neustupov.votingForRestaurants.model.Menu;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer>{
}
