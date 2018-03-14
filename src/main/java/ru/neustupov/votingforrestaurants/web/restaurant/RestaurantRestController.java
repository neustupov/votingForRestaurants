package ru.neustupov.votingForRestaurants.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.neustupov.votingForRestaurants.AuthorizedUser;
import ru.neustupov.votingForRestaurants.model.Restaurant;
import ru.neustupov.votingForRestaurants.service.RestaurantService;
import ru.neustupov.votingForRestaurants.service.VoteService;
import ru.neustupov.votingForRestaurants.to.RestaurantWithVotes;
import ru.neustupov.votingForRestaurants.util.RestaurantsUtil;

import java.util.List;

import static ru.neustupov.votingForRestaurants.util.ValidationUtil.assureIdConsistent;
import static ru.neustupov.votingForRestaurants.util.ValidationUtil.checkNew;

@Controller
public class RestaurantRestController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    private RestaurantService service;

    private VoteService voteService;

    @Autowired
    public RestaurantRestController(RestaurantService service, VoteService voteService){
        this.service = service;
        this.voteService = voteService;
    }

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        log.info("create {}", restaurant);
        return service.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id = {}", restaurant, id);
        assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public Restaurant get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public List<RestaurantWithVotes> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll restaurants for user{}", userId);
        return RestaurantsUtil.getWithVotes(service.getAll(),voteService.getAll());
    }
}
