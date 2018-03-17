package ru.neustupov.votingforrestaurants.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.model.Vote;
import ru.neustupov.votingforrestaurants.util.exception.NotFoundException;

import static ru.neustupov.votingforrestaurants.UserTestData.ADMIN_ID;
import static ru.neustupov.votingforrestaurants.UserTestData.USER_ID;
import static ru.neustupov.votingforrestaurants.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest{

    @Autowired
    private VoteService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(VOTE1_ID, USER_ID);
        assertMatch(service.getAll(), VOTES);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(VOTE1_ID, 1);
    }

    @Test
    public void testSave() throws Exception {
        Vote created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(), VOTE1, VOTE2, VOTE3, VOTE4, VOTE5, VOTE6, created);
    }

    @Test
    public void testGet() throws Exception {
        Vote actual = service.get(VOTE1_ID, USER_ID);
        assertMatch(actual, VOTE1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(VOTE1_ID, ADMIN_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        assertMatch(service.getAll(),VOTE1, VOTE2, VOTE3, VOTE4, VOTE5, VOTE6);
    }
}