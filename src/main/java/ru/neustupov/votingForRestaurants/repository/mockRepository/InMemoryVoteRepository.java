package ru.neustupov.votingForRestaurants.repository.mockRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.neustupov.votingForRestaurants.AuthorizedUser;
import ru.neustupov.votingForRestaurants.model.Vote;
import ru.neustupov.votingForRestaurants.repository.VoteRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryVoteRepository implements VoteRepository{

    private static final Logger log = LoggerFactory.getLogger(InMemoryVoteRepository.class);

    private Map<Integer, Map<Integer, Vote>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new Vote(LocalDateTime.of(2015, Month.MAY, 30, 10, 0)),
                AuthorizedUser.id());
        save(new Vote(LocalDateTime.of(2015, Month.JULY, 10, 11, 0)),
                AuthorizedUser.id());
    }

    @Override
    public Vote save(Vote vote, int userId) {
        Map<Integer, Vote> votes = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (vote.isNew()) {
            vote.setId(counter.incrementAndGet());
            votes.put(vote.getId(), vote);
            log.info("create {} for user {} in InMemoryRepository", vote, userId);
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
                        .sorted(Comparator.comparing(Vote::getDateTime));
    }
}
