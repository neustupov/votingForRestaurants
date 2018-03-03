package ru.neustupov.votingForRestaurants.repository.mockRepository;

import ru.neustupov.votingForRestaurants.model.Vote;
import ru.neustupov.votingForRestaurants.repository.VoteRepository;
import ru.neustupov.votingForRestaurants.util.MockUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.neustupov.votingForRestaurants.repository.mockRepository.InMemoryUserRepository.USER_ID;

public class InMemoryVoteRepository implements VoteRepository{

    private Map<Integer, Map<Integer, Vote>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Vote save(Vote vote, int userId) {
        Map<Integer, Vote> votes = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (vote.isNew()) {
            vote.setId(counter.incrementAndGet());
            votes.put(vote.getId(), vote);
            return vote;
        }
        return votes.computeIfPresent(vote.getId(), (id, oldVote) -> vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Vote> votes = repository.get(userId);
        return votes != null && votes.remove(id) != null;
    }

    @Override
    public Vote get(int id, int userId) {
        Map<Integer, Vote> votes = repository.get(userId);
        return votes == null ? null : votes.get(id);
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        return getAllAsStreamForUserId(userId).collect(Collectors.toList());
    }

    @Override
    public List<Vote> getAllByRest(int restId) {
        return null;
    }

    @Override
    public Vote getWithRestaurant(int id, int restId) {
        return null;
    }

    @Override
    public Vote getWithUser(int id, int userId) {
        return null;
    }

    @Override
    public Vote getWithRestaurantAndUser(int id, int restId, int userId) {
        return null;
    }

    private Stream<Vote> getAllAsStreamForUserId(int userId) {
        Map<Integer, Vote> votes = repository.get(userId);
        return votes == null ?
                Stream.empty() :
                votes.values().stream()
                        .sorted(Comparator.comparing(Vote::getDateTime).reversed());
    }
}
