package ru.neustupov.votingForRestaurants.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.neustupov.votingForRestaurants.model.Vote;
import ru.neustupov.votingForRestaurants.repository.VoteRepository;

import java.util.List;

@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Override
    public Vote save(Vote vote) {
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return crudVoteRepository.delete(id) != 0;
    }

    @Override
    public Vote get(int id) {
        return crudVoteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vote> getAll() {
        return crudVoteRepository.findAll();
    }

    @Override
    public Vote getWithRestaurant(int id) {
        return crudVoteRepository.getWithRestaurant(id);
    }

    @Override
    public Vote getWithUser(int id) {
        return crudVoteRepository.getWithUser(id);
    }

    @Override
    public Vote getWithRestaurantAndUser(int id) {
        return crudVoteRepository.getWithRestaurantAndUser(id);
    }
}
