package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.neustupov.votingForRestaurants.repository.MenuRepository;

@Repository
public class DataJpaMenuRepositoryImpl implements MenuRepository{

    @Autowired
    private CrudMenuRepository crudMenuRepository;
}
