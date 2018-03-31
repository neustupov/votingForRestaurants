package ru.neustupov.votingforrestaurants.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.model.Meal;
import ru.neustupov.votingforrestaurants.service.MealService;

import java.util.List;

import static ru.neustupov.votingforrestaurants.util.ValidationUtil.assureIdConsistent;
import static ru.neustupov.votingforrestaurants.util.ValidationUtil.checkNew;

public class AbstractMealController {

    private static final Logger log = LoggerFactory.getLogger(AbstractMealController.class);

    @Autowired
    private MealService service;

    public Meal create(Meal meal, int menuId){
        checkNew(meal);
        log.info("create {} for menu {}", meal, menuId);
        return service.create(meal, menuId);
    }

    public void delete(int id, int menuId){
        log.info("delete meal {} for menu {}", id, menuId);
        service.delete(id, menuId);
    }

    public Meal get(int id, int menuId) {
        log.info("get meal {} for menu {}", id, menuId);
        return service.get(id, menuId);
    }

    public void update(Meal meal, int mealId, int menuId){
        assureIdConsistent(meal, mealId);
        log.info("update {} for menu {}", meal, menuId);
        service.update(meal, menuId);
    }

    public List<Meal> getAll(int menuId){
        log.info("getAll meals for menu {}", menuId);
        return service.getAll(menuId);
    }
}