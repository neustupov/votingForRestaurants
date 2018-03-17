package ru.neustupov.votingforrestaurants.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.model.Meal;
import ru.neustupov.votingforrestaurants.util.exception.NotFoundException;

import static ru.neustupov.votingforrestaurants.MealTestData.*;
import static ru.neustupov.votingforrestaurants.MenuTestData.RUSSIA_MENU_ID1;
import static ru.neustupov.votingforrestaurants.MealTestData.assertMatch;

public class MealServiceTest extends AbstractServiceTest{

    @Autowired
    private MealService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(APPLE_ID, RUSSIA_MENU_ID1);
        assertMatch(service.getAll(RUSSIA_MENU_ID1), BOTTLE_OF_WATER);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(APPLE_ID, 1);
    }

    @Test
    public void testSave() throws Exception {
        Meal created = getCreated();
        service.create(created, RUSSIA_MENU_ID1);
        assertMatch(service.getAll(RUSSIA_MENU_ID1), APPLE, BOTTLE_OF_WATER, created);
    }

    @Test
    public void testGet() throws Exception {
        Meal actual = service.get(APPLE_ID, RUSSIA_MENU_ID1);
        assertMatch(actual, APPLE);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(BANANAS_ID, RUSSIA_MENU_ID1);
    }

    @Test
    public void testGetAll() throws Exception {
        assertMatch(service.getAll(RUSSIA_MENU_ID1), APPLE, BOTTLE_OF_WATER);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();
        service.update(updated, RUSSIA_MENU_ID1);
        assertMatch(service.get(APPLE_ID, RUSSIA_MENU_ID1), updated);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        service.update(BANANAS, RUSSIA_MENU_ID1);
    }
}
