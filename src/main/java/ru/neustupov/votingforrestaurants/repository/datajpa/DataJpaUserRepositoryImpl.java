package ru.neustupov.votingforrestaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.neustupov.votingforrestaurants.model.User;
import ru.neustupov.votingforrestaurants.repository.UserRepository;

import java.util.List;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Transactional
    @Override
    public User save(User user) {
        return crudUserRepository.save(user);
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.findAll();
    }

    @Override
    public User getWithVotes(int id) {
        return crudUserRepository.getWithVotes(id);
    }
}