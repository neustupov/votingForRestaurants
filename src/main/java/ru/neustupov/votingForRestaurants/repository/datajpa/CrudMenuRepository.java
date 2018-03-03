package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.neustupov.votingForRestaurants.model.Menu;

import java.util.List;
import java.util.Optional;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer>{

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restId=:restId")
    int delete(@Param("id") int id, @Param("restId") int restId);

    @Override
    @Transactional
    Menu save(Menu user);

    @Query("SELECT m FROM Menu m WHERE m.id=:id AND m.restId=:restId")
    Optional<Menu> get(@Param("id") int id,@Param("restId") int restId);

    List<Menu> findAllByIdRest(int idRest);

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.id=?1")
    Menu getWithRestaurant(int id);

    @EntityGraph(attributePaths = {"meals"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.id=?1")
    Menu getWithMeals(int id);

    @EntityGraph(attributePaths = {"restaurant","meals"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.id=?1")
    Menu getWithRestaurantAndMeals(int id);
}
