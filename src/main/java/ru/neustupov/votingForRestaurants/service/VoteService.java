package ru.neustupov.votingForRestaurants.service;

import ru.neustupov.votingForRestaurants.model.Vote;
import ru.neustupov.votingForRestaurants.util.exception.NotFoundException;

import java.util.List;

public interface VoteService {

    Vote create(Vote vote);

    void delete(int id) throws NotFoundException;

    Vote get(int id) throws NotFoundException;

    void update(Vote vote);

    List<Vote> getAll();

    Vote getWithUser(int id);

    Vote getWithRestaurant(int id);

    Vote getWithUserAndRestaurant(int id);
}
