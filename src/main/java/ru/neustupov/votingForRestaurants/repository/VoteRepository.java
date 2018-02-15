package ru.neustupov.votingForRestaurants.repository;

import ru.neustupov.votingForRestaurants.model.Vote;

import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    // false if not found
    boolean delete(int id);

    // null if not found
    Vote get(int id);

    List<Vote> getAll();

    default Vote getWithRestaurant(int id){
        throw new UnsupportedOperationException();
    }

    default Vote getWithUser(int id){
        throw new UnsupportedOperationException();
    }

    default Vote getWithRestaurantAndUser(int id){throw new UnsupportedOperationException();}
}
