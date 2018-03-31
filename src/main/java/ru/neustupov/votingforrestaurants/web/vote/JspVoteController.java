package ru.neustupov.votingforrestaurants.web.vote;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.neustupov.votingforrestaurants.model.Vote;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Controller
@RequestMapping(value = "/votes")
public class JspVoteController extends AbstractVoteController{

    @GetMapping
    public String votes(Model model) {
        model.addAttribute("votesList", super.getAll());
        return "votes";
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        super.create(new Vote(Date.from(Instant.now())), getId(request, "restId"));
        model.addAttribute("vote", new Vote());
        return "redirect:/restaurants";
    }

    private int getId(HttpServletRequest request, String param) {
        String paramId = Objects.requireNonNull(request.getParameter(param));
        return Integer.valueOf(paramId);
    }
}
