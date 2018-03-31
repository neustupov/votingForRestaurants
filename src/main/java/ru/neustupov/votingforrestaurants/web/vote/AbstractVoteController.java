package ru.neustupov.votingforrestaurants.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.AuthorizedUser;
import ru.neustupov.votingforrestaurants.model.Vote;
import ru.neustupov.votingforrestaurants.service.VoteService;

import java.util.List;

import static ru.neustupov.votingforrestaurants.util.ValidationUtil.assureIdConsistent;
import static ru.neustupov.votingforrestaurants.util.ValidationUtil.checkNew;

public abstract class AbstractVoteController {

    private static final Logger log = LoggerFactory.getLogger(VoteRestController.class);

    @Autowired
    private VoteService service;

    public Vote get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get vote {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete vote {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Vote> getAll() {
        log.info("getAll votes");
        return service.getAll();
    }

    public List<Vote> getAllByRest(int restId){
        log.info("getAllByRest {}", restId);
        return service.getAllByRest(restId);
    }

    public Vote create(Vote vote, int restId) {
        int userId = AuthorizedUser.id();
        checkNew(vote);
        log.info("create {} for user {}", vote, userId);
        return service.create(vote, userId, restId);
    }

    public void update(Vote vote, int restId) {
        int userId = AuthorizedUser.id();
        assureIdConsistent(vote, userId);
        log.info("update {} for user {} and restaurant {}", vote, userId, restId);
        service.update(vote, userId, restId);
    }
}
