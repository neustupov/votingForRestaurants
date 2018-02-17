package ru.neustupov.votingForRestaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.neustupov.votingForRestaurants.model.Vote;
import ru.neustupov.votingForRestaurants.repository.VoteRepository;
import ru.neustupov.votingForRestaurants.util.exception.NotFoundException;

import java.util.List;

import static ru.neustupov.votingForRestaurants.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService{

    private final VoteRepository repository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository){
        this.repository = repository;
    }

    @Override
    public Vote create(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Vote get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void update(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(repository.save(vote), vote.getId());
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public Vote getWithUser(int id) {
        return checkNotFoundWithId(repository.getWithUser(id), id);
    }

    @Override
    public Vote getWithRestaurant(int id) {
        return checkNotFoundWithId(repository.getWithRestaurant(id), id);
    }

    @Override
    public Vote getWithUserAndRestaurant(int id) {
        return checkNotFoundWithId(repository.getWithRestaurantAndUser(id), id);
    }
}
