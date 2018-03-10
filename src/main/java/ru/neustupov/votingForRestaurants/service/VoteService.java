package ru.neustupov.votingForRestaurants.service;

import ru.neustupov.votingForRestaurants.model.Vote;
import ru.neustupov.votingForRestaurants.util.exception.NotFoundException;

import java.util.List;

public interface VoteService {

    Vote create(Vote vote, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Vote get(int id, int userId) throws NotFoundException;

    void update(Vote vote, int userId);

    List<Vote> getAll();

    List<Vote> getAllByUser(int userId);

    List<Vote> getAllByRest(int restId);

    Vote getWithUser(int id, int userId);

    Vote getWithRestaurant(int id, int restId);

    Vote getWithRestaurantAndUser(int id, int restId, int userId);
}
