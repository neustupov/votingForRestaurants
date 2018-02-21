package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.neustupov.votingForRestaurants.model.Meal;

import java.util.List;
import java.util.Optional;

public interface CrudMealRepository extends JpaRepository<Meal, Integer>{

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.idMenu=:menuId")
    int delete(@Param("id") int id, @Param("menuId") int menuId);

    @Override
    @Transactional
    Meal save(Meal user);

    @Override
    Optional<Meal> findById(Integer id);

    @Override
    List<Meal> findAll(Sort sort);

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Meal m WHERE m.id=?1")
    Meal getWithMenu(int id);
}
