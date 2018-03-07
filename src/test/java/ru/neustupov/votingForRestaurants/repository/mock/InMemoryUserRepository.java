package ru.neustupov.votingForRestaurants.repository.mock;

import org.springframework.stereotype.Repository;
import ru.neustupov.votingForRestaurants.UserTestData;
import ru.neustupov.votingForRestaurants.model.User;
import ru.neustupov.votingForRestaurants.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.neustupov.votingForRestaurants.UserTestData.*;

@Repository
public class InMemoryUserRepository implements UserRepository{

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public void init()
    {
        repository.clear();
        repository.put(USER_ID, USER);
        repository.put(ADMIN_ID, ADMIN);
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return repository.values().stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
    }

    @Override
    public User getWithVotes(int id) {
        return null;
    }
}
