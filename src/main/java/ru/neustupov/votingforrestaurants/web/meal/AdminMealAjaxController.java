package ru.neustupov.votingforrestaurants.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.neustupov.votingforrestaurants.model.Meal;

import java.util.List;

@RestController
@RequestMapping(value = "/ajax/admin/meals")
public class AdminMealAjaxController extends AbstractMealController{

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable("id") int id,
                    @RequestParam("menuId") int menuId) {
        return super.get(id, menuId);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getAll(@RequestParam("menuId") int menuId) {
        return super.getAll(menuId);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id,
                       @RequestParam("menuId") int menuId) {
        super.delete(id, menuId);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("price") int price,
                               @RequestParam("menuId") int menuId
                               ) {
        Meal meal = new Meal(id, name, price);
        if (meal.isNew()) {
            super.create(meal, menuId);
        }
    }
}
