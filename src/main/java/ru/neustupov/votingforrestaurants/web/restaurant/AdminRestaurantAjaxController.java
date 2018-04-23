package ru.neustupov.votingforrestaurants.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.neustupov.votingforrestaurants.model.Restaurant;
import ru.neustupov.votingforrestaurants.to.RestaurantWithVotes;

import java.util.List;

@RestController
@RequestMapping(value = "/ajax/admin/restaurants")
public class AdminRestaurantAjaxController extends AbstractRestaurantController{

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantWithVotes> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(Restaurant restaurant) {
        if (restaurant.isNew()) {
            super.create(restaurant);
        }
    }
}
