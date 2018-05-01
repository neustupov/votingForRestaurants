package ru.neustupov.votingforrestaurants.web.vote;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.neustupov.votingforrestaurants.model.Vote;

import java.sql.Date;
import java.time.Instant;

@RestController
@RequestMapping("/ajax/profile/votes")
public class ProfileVoteAjaxController extends AbstractVoteController{

    @PostMapping
    public void createOrUpdate(@RequestParam("restId") Integer restId) {

        Vote vote = super.getByUserIdAndDate();
        if (vote == null) {
            super.create(new Vote(Date.from(Instant.now())), restId);
        }else {
            super.update(vote.getId(), vote, restId);
        }
    }
}
