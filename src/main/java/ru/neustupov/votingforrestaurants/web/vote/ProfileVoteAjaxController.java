package ru.neustupov.votingforrestaurants.web.vote;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajax/profile/votes")
public class ProfileVoteAjaxController extends AbstractVoteController {

    @PostMapping
    public void createOrUpdate(@RequestParam("restId") Integer restId) {

        super.createOrUpdate(restId);
    }
}
