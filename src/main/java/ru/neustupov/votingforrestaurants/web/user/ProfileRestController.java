package ru.neustupov.votingforrestaurants.web.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.neustupov.votingforrestaurants.AuthorizedUser;
import ru.neustupov.votingforrestaurants.View;
import ru.neustupov.votingforrestaurants.model.User;
import ru.neustupov.votingforrestaurants.to.UserTo;

import javax.validation.Valid;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController{

    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return super.get(authorizedUser.getId());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.delete(authorizedUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(View.Web.class) @RequestBody UserTo userTo,
                       @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.update(userTo, authorizedUser.getId());
    }
}
