package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.neustupov.votingForRestaurants.repository.UserRepository;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository{

    @Autowired
    private CrudUserRepository crudUserRepository;
}
