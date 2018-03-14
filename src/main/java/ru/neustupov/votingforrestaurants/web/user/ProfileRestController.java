package ru.neustupov.votingforrestaurants.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.neustupov.votingforrestaurants.AuthorizedUser;
import ru.neustupov.votingforrestaurants.model.User;
import ru.neustupov.votingforrestaurants.service.UserService;

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
