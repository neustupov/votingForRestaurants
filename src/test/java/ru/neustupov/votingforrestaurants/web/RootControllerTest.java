package ru.neustupov.votingforrestaurants.web;

import org.junit.Ignore;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.neustupov.votingforrestaurants.TestUtil.userAuth;
import static ru.neustupov.votingforrestaurants.UserTestData.ADMIN;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testRootAdmin() throws Exception {
        mockMvc.perform(get("/")
                .with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("restaurants"));
    }

    @Ignore
    @Test
    public void testGetRegister() throws Exception {
        mockMvc.perform(get("/register")
                .with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("restaurants"));
    }

    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get("/users")
                .with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"));
    }

    @Test
    public void testUnAuth() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testProfileRestaurants() throws Exception {
        mockMvc.perform(get("/profileRestaurants")
                .with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("profileRestaurants"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/profileRestaurants.jsp"));
    }

    //TODO need add all tests for other methods
}
