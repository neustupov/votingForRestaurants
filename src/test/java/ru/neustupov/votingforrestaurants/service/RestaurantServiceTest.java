package ru.neustupov.votingforrestaurants.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import ru.neustupov.votingforrestaurants.model.Restaurant;
import ru.neustupov.votingforrestaurants.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.neustupov.votingforrestaurants.RestaurantTestData.*;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("restaurants").clear();
    }

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "New");
        Restaurant created = service.create(newRestaurant);
        newRestaurant.setId(created.getId());
        assertMatch(service.getAll(), RUSSIA, UKRAINE, U_KOLYANA, ALMAZ, FART, newRestaurant);
    }

    @Test
    void duplicateNameCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new Restaurant(null, "Russia")));
    }

    @Test
    void delete() throws Exception {
        service.delete(RUSSIA_ID);
        assertMatch(service.getAll(), UKRAINE, U_KOLYANA, ALMAZ, FART);
    }

    @Test
    void notFoundDelete() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(1));
    }

    @Test
    void get() throws Exception {
        Restaurant restaurant = service.get(UKRAINE_ID);
        assertMatch(restaurant, UKRAINE);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1));
    }

    @Test
    void update() throws Exception {
        Restaurant updated = new Restaurant(U_KOLYANA);
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(service.get(U_KOLYANA_ID), updated);
    }

    @Test
    void getAll() throws Exception {
        List<Restaurant> all = service.getAll();
        assertMatch(all, RUSSIA, UKRAINE, U_KOLYANA, ALMAZ, FART);
    }

    @Test
    void testValidation() throws Exception {
        validateRootCause(() -> service.create(new Restaurant("   ")), ConstraintViolationException.class);
    }
}
