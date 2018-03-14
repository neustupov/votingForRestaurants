package ru.neustupov.votingforrestaurants.web;

import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.neustupov.votingforrestaurants.UserTestData;
import ru.neustupov.votingforrestaurants.model.User;
import ru.neustupov.votingforrestaurants.repository.mock.InMemoryUserRepository;
import ru.neustupov.votingforrestaurants.util.exception.NotFoundException;
import ru.neustupov.votingforrestaurants.web.user.AdminRestController;

import java.util.Arrays;
import java.util.Collection;

import static ru.neustupov.votingforrestaurants.UserTestData.ADMIN;

public class InMemoryAdminRestControllerTest {

    private static ConfigurableApplicationContext appCtx;
    private static AdminRestController controller;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/mock.xml");
        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
        controller = appCtx.getBean(AdminRestController.class);
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
    }

    @Before
    public void setUp() throws Exception {
        // re-initialize
        InMemoryUserRepository repository = appCtx.getBean(InMemoryUserRepository.class);
        repository.init();
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(UserTestData.USER_ID);
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.iterator().next(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(10);
    }
}
