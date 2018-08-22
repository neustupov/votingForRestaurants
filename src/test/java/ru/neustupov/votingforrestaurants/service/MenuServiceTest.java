package ru.neustupov.votingforrestaurants.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.model.Meal;
import ru.neustupov.votingforrestaurants.model.Menu;
import ru.neustupov.votingforrestaurants.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.neustupov.votingforrestaurants.MenuTestData.*;
import static ru.neustupov.votingforrestaurants.RestaurantTestData.RUSSIA_ID;
import static ru.neustupov.votingforrestaurants.MealTestData.MEAL_IN_MENU;

class MenuServiceTest extends AbstractServiceTest {
    @Autowired
    private MenuService service;

    @Test
    void delete() throws Exception {
        service.delete(RUSSIA_MENU_ID1, RUSSIA_ID);
        assertMatch(service.getAll(RUSSIA_ID), RUSSIA_MENU2, MENU_TODAYS_WITH_MEALS);
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(RUSSIA_MENU_ID1, 1));
    }

    @Test
    void create() throws Exception {
        Menu created = getCreated();
        service.create(created, RUSSIA_ID);
        assertMatch(service.getAll(RUSSIA_ID), RUSSIA_MENU1, RUSSIA_MENU2, MENU_TODAYS_WITH_MEALS, created);
    }

    @Test
    void get() throws Exception {
        Menu actual = service.get(RUSSIA_MENU_ID1, RUSSIA_ID);
        assertMatch(actual, RUSSIA_MENU1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(UKRAINE_MENU_ID, RUSSIA_ID));
    }

    @Test
    void getAll() throws Exception {
        assertMatch(service.getAll(RUSSIA_ID), RUSSIA_MENU1, RUSSIA_MENU2, MENU_TODAYS_WITH_MEALS);
    }

    @Test
    void getTodaysMenuWithMeals() throws Exception {
        Menu menu = service.getTodaysMenuWithMeals(100002);
        assertMatch(MENU_TODAYS_WITH_MEALS, menu);
        ru.neustupov.votingforrestaurants.MealTestData.assertMatch(MEAL_IN_MENU, (Meal) menu.getMeals().toArray()[0]);
    }
}
