package ru.neustupov.votingForRestaurants.service;

import ru.neustupov.votingForRestaurants.model.User;
import ru.neustupov.votingForRestaurants.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    void update(User user);

    List<User> getAll();

    User getWithVotes(int id);
}
