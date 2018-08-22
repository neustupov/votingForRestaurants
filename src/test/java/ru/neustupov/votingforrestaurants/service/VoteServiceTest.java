package ru.neustupov.votingforrestaurants.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.model.Vote;
import ru.neustupov.votingforrestaurants.util.exception.NotFoundException;

import java.sql.Date;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.neustupov.votingforrestaurants.RestaurantTestData.RUSSIA_ID;
import static ru.neustupov.votingforrestaurants.UserTestData.ADMIN_ID;
import static ru.neustupov.votingforrestaurants.UserTestData.USER_ID;
import static ru.neustupov.votingforrestaurants.VoteTestData.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void delete() throws Exception {
        service.delete(VOTE4_ID, ADMIN_ID);
        assertMatch(service.getAll(), VOTES);
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(VOTE1_ID, 1));
    }

    @Test
    void create() throws Exception {
        Vote created = getCreated();
        service.create(created, USER_ID, RUSSIA_ID);
        assertMatch(service.getAll(), VOTE1, VOTE2, VOTE3, VOTE4, VOTE5, VOTE6, created);
    }

    @Test
    void get() throws Exception {
        Vote actual = service.get(VOTE1_ID, USER_ID);
        assertMatch(actual, VOTE1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(VOTE1_ID, ADMIN_ID));
    }

    @Test
    void getAll() throws Exception {
        assertMatch(service.getAll(), VOTE1, VOTE2, VOTE3, VOTE4, VOTE5, VOTE6);
    }

    @Test
    void getByUserIdAndDate() throws Exception {
        service.create(new Vote(Date.from(Instant.now())), ADMIN_ID, RUSSIA_ID);
        assertMatch(service.getByUserIdAndDate(ADMIN_ID), VOTE_FOR_GET_BY_USER_ID_AND_DATE);
    }

    @Test
    void getByUserIdAndDateNotFound() throws Exception {
        assertMatch(service.getByUserIdAndDate(ADMIN_ID), null);
    }
}
