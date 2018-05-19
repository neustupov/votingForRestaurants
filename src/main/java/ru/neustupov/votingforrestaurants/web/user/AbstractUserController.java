package ru.neustupov.votingforrestaurants.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.model.User;
import ru.neustupov.votingforrestaurants.service.UserService;
import ru.neustupov.votingforrestaurants.to.UserTo;

import java.util.List;

import static ru.neustupov.votingforrestaurants.util.ValidationUtil.assureIdConsistent;
import static ru.neustupov.votingforrestaurants.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public static final String EXCEPTION_DUPLICATE_EMAIL = "exception.user.duplicateEmail";

    @Autowired
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

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        service.update(userTo);
    }

    public void enable(int id, boolean enabled) {
        log.info((enabled ? "enable " : "disable ") + id);
        service.enable(id, enabled);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
