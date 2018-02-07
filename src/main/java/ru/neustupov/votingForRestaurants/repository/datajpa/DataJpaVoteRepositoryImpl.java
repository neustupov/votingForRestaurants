package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.neustupov.votingForRestaurants.repository.VoteRepository;

@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository crudVoteRepository;
}
