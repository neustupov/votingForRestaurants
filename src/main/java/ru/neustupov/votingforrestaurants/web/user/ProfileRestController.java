package ru.neustupov.votingForRestaurants.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.neustupov.votingForRestaurants.AuthorizedUser;
import ru.neustupov.votingForRestaurants.model.User;
import ru.neustupov.votingForRestaurants.service.UserService;

@Controller
public class ProfileRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private UserService service;

    @Autowired
    public ProfileRestController(UserService service) {
        this.service = service;
    }

    public User get() {
        return service.get(AuthorizedUser.id());
    }

    public void delete() {
        service.delete(AuthorizedUser.id());
    }

    public void update(User user) {
        service.update(user);
    }
}
