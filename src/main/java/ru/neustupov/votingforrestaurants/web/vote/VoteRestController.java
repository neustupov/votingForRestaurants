package ru.neustupov.votingForRestaurants.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.neustupov.votingForRestaurants.AuthorizedUser;
import ru.neustupov.votingForRestaurants.model.Vote;
import ru.neustupov.votingForRestaurants.service.VoteService;

import java.util.List;

import static ru.neustupov.votingForRestaurants.util.ValidationUtil.assureIdConsistent;
import static ru.neustupov.votingForRestaurants.util.ValidationUtil.checkNew;

@Controller
public class VoteRestController {

    private static final Logger log = LoggerFactory.getLogger(VoteRestController.class);

    private final VoteService service;

    @Autowired
    public VoteRestController(VoteService service) {
        this.service = service;
    }

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
        /*int userId = AuthorizedUser.id();*/
        /*log.info("getAll for user {}", userId);*/
        log.info("getAll votes");
        return service.getAll();
    }

    public List<Vote> getAllByRest(int restId){
        log.info("getAllByRest {}", restId);
        return service.getAllByRest(restId);
    }

    public Vote create(Vote vote) {
        int userId = AuthorizedUser.id();
        checkNew(vote);
        log.info("create {} for user {}", vote, userId);
        return service.create(vote, userId);
    }

    public void update(Vote vote, int id) {
        int userId = AuthorizedUser.id();
        assureIdConsistent(vote, id);
        log.info("update {} for user {}", vote, userId);
        service.update(vote, userId);
    }
}
