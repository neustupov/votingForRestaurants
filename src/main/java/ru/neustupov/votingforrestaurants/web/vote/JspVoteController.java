package ru.neustupov.votingforrestaurants.web.vote;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.neustupov.votingforrestaurants.model.Vote;
import ru.neustupov.votingforrestaurants.util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Controller
@RequestMapping(value = "/votes")
public class JspVoteController extends AbstractVoteController {

    @GetMapping
    public String votes(Model model) {
        model.addAttribute("votesList", super.getAll());
        return "votes";
    }

    @GetMapping("/updateOrCreate")
    public String updateOrCreate(HttpServletRequest request) {

        Vote vote = super.getByUserIdAndDate();

        if (vote == null) {
            super.create(new Vote(Date.from(Instant.now())), getId(request, "restId"));
        } else {
            ValidationUtil.checkTimeForVote();
            super.update(vote, getId(request, "restId"));
        }
        return "redirect:/restaurants";
    }
    private int getId(HttpServletRequest request, String param) {
        String paramId = Objects.requireNonNull(request.getParameter(param));
        return Integer.valueOf(paramId);
    }
}
