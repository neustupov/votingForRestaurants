package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neustupov.votingForRestaurants.model.Vote;

public interface CrudVoteRepository extends JpaRepository<Vote, Integer>{
}
