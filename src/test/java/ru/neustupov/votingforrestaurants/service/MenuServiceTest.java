package ru.neustupov.votingforrestaurants.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.model.Menu;
import ru.neustupov.votingforrestaurants.util.exception.NotFoundException;

import static ru.neustupov.votingforrestaurants.MenuTestData.*;
import static ru.neustupov.votingforrestaurants.RestaurantTestData.RUSSIA_ID;

public class MenuServiceTest extends AbstractServiceTest{
    @Autowired
    private MenuService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(RUSSIA_MENU_ID1, RUSSIA_ID);
        assertMatch(service.getAll(RUSSIA_ID), RUSSIA_MENU2);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(RUSSIA_MENU_ID1, 1);
    }

    @Test
    public void testSave() throws Exception {
        Menu created = getCreated();
        service.create(created, RUSSIA_ID);
        assertMatch(service.getAll(RUSSIA_ID), RUSSIA_MENU1, RUSSIA_MENU2, created);
    }

    @Test
    public void testGet() throws Exception {
        Menu actual = service.get(RUSSIA_MENU_ID1, RUSSIA_ID);
        assertMatch(actual, RUSSIA_MENU1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(UKRAINE_MENU_ID, RUSSIA_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        assertMatch(service.getAll(RUSSIA_ID), RUSSIA_MENU1, RUSSIA_MENU2);
    }
}
