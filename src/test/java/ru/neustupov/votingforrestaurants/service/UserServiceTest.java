package ru.neustupov.votingforrestaurants.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import ru.neustupov.votingforrestaurants.model.Role;
import ru.neustupov.votingforrestaurants.model.User;
import ru.neustupov.votingforrestaurants.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.neustupov.votingforrestaurants.UserTestData.*;

class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    void create() throws Exception {
        User newUser = new User(null, "New", "new@yandex.ru", "newPass", Date.from(Instant.now()), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }

    @Test
    void duplicateEmailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "User", "user@yandex.ru", "password",
                        Date.from(Instant.now()), Collections.singleton(Role.ROLE_USER))));
    }

    @Test
    void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test
    void notFoundDelete() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(1));
    }

    @Test
    void get() throws Exception {
        User user = service.get(ADMIN_ID);
        assertMatch(user, ADMIN);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1));
    }

    @Test
    void update() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }

    //TODO need repair password validation
    @Test
    void testValidation() throws Exception {
        validateRootCause(() -> service.create(new User(100500, "   ", "new@yandex.ru", "Password", Date.from(Instant.now()), EnumSet.of(Role.ROLE_USER))), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(100500, "TestUser", "yandex.ru", "Password", Date.from(Instant.now()), EnumSet.of(Role.ROLE_USER))), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(100500, "TestUser", "new@yandex.ru", "", Date.from(Instant.now()), EnumSet.of(Role.ROLE_USER))), ConstraintViolationException.class);
        // validateRootCause(() -> service.create(new User(100500, "TestUser", "new@yandex.ru","Pass", Date.from(Instant.now()), EnumSet.of(Role.ROLE_USER))), ConstraintViolationException.class);
        // validateRootCause(() -> service.create(new User(100500, "TestUser", "new@yandex.ru","1234567890qwertyuiopsdfghjklzxcvbnmnbvcxzasdfghjklpoiuytrewq12345qwertyuioplkjhgfdsazxcvbnmnbvcxzasdf", Date.from(Instant.now()), EnumSet.of(Role.ROLE_USER))), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(100500, "TestUser", "new@yandex.ru", "Password", null, EnumSet.of(Role.ROLE_USER))), ConstraintViolationException.class);
    }

    @Test
    void testEnable() {
        service.enable(USER_ID, false);
        Assertions.assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        Assertions.assertTrue(service.get(USER_ID).isEnabled());
    }

    @Test
    void getByEmail() throws Exception {
        User user = service.getByEmail("admin@yandex.ru");
        assertMatch(user, ADMIN);
    }

}

