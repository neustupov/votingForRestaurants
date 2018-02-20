package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.neustupov.votingForRestaurants.model.Vote;

import java.util.List;
import java.util.Optional;

@Transactional
public interface CrudVoteRepository extends JpaRepository<Vote, Integer>{

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId AND v.restaurant.id=:restId")
    int delete(@Param("id") int id, @Param("userId") int userId, @Param("restId") int restId);

    @Override
    @Transactional
    Vote save(Vote vote);

    @Override
    Optional<Vote> findById(Integer id);

    @Override
    List<Vote> findAll(Sort sort);

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM Vote v WHERE v.id=?1")
    Vote getWithRestaurant(int id);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM Vote v WHERE v.id=?1")
    Vote getWithUser(int id);

    @EntityGraph(attributePaths = {"restaurant","user"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM Vote v WHERE v.id=?1")
    Vote getWithRestaurantAndUser(int id);
}
