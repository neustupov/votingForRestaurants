package ru.neustupov.votingForRestaurants.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.neustupov.votingForRestaurants.model.User;
import ru.neustupov.votingForRestaurants.service.UserService;

import java.util.List;

import static ru.neustupov.votingForRestaurants.util.ValidationUtil.assureIdConsistent;
import static ru.neustupov.votingForRestaurants.util.ValidationUtil.checkNew;

public class AdminRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }
}
