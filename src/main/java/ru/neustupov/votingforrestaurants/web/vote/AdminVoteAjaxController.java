package ru.neustupov.votingforrestaurants.web.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.neustupov.votingforrestaurants.model.Vote;
import java.util.List;

@RestController
@RequestMapping("/ajax/admin/votes")
public class AdminVoteAjaxController extends AbstractVoteController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("restId") Integer restId) {

        super.createOrUpdate(restId);
    }
}
