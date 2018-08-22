package ru.neustupov.votingforrestaurants.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.neustupov.votingforrestaurants.UserTestData;
import ru.neustupov.votingforrestaurants.model.User;
import ru.neustupov.votingforrestaurants.repository.mock.InMemoryUserRepository;
import ru.neustupov.votingforrestaurants.util.exception.NotFoundException;
import ru.neustupov.votingforrestaurants.web.user.AdminRestController;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.neustupov.votingforrestaurants.UserTestData.ADMIN;

@ContextConfiguration({"classpath:spring/mock.xml",
        "classpath:spring/spring-security.xml"})
@ExtendWith(SpringExtension.class)
class InMemoryAdminRestControllerSpringTest {

    @Autowired
    private AdminRestController controller;

    @Autowired
    private InMemoryUserRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        repository.init();
    }

    @Test
    void testDelete() throws Exception {
        controller.delete(UserTestData.USER_ID);
        Collection<User> users = controller.getAll();
        Assertions.assertEquals(users.size(), 1);
        Assertions.assertEquals(users.iterator().next(), ADMIN);
    }

    @Test
    void testDeleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                controller.delete(10));
    }
}
