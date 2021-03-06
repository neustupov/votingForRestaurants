package ru.neustupov.votingforrestaurants.web;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.service.RestaurantService;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static ru.neustupov.votingforrestaurants.TestUtil.userHttpBasic;
import static ru.neustupov.votingforrestaurants.UserTestData.ADMIN;

class RestaurantControllerTest extends AbstractControllerTest {

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void testRestaurants() throws Exception {
        mockMvc.perform(get("/restaurants"))
                .andDo(print())
                .andExpect(status().isFound());
    }

    //TODO understand what the problem is
    @Disabled
    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/ajax/admin/restaurants/100002"))
                .andDo(print())
                .andExpect(status().isFound());

        assertThat(restaurantService.getAll(), hasSize(4));
    }

    @Disabled
    @Test
    void testUpdate() throws Exception {
        mockMvc.perform(get("/restaurants/update").param("id", "100002"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("restaurantForm"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/restaurantForm.jsp"))
                .andExpect(model().attribute("restaurant",
                        hasProperty("id", is(100002))
                ))
                .andExpect(model().attribute("restaurant",
                        hasProperty("name", is("Russia"))));
    }

    @Test
    void testCreate() throws Exception {
        mockMvc.perform(post("/ajax/admin/restaurants")
                .param("name", "newRest")
                .param("id", "")
                .with(userHttpBasic(ADMIN))
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isFound());
    }

    //TODO understand what the problem is
    @Disabled
    @Test
    void testCreateOrUpdateOnlyCreate() throws Exception {
        mockMvc.perform(post("/ajax/admin/restaurants")
                .param("name", "Russia1")
                .param("id", ""))
                .andDo(print())
                .andExpect(status().isFound());

        assertThat(restaurantService.getAll(), hasSize(6));
        assertThat(restaurantService.getAll(), hasItem(
                allOf(hasProperty("name", is("Russia1")))
        ));
    }

    //TODO understand what the problem is
    @Disabled
    @Test
    void testCreateOrUpdateOnlyUpdate() throws Exception {
        mockMvc.perform(post("/ajax/admin/restaurants/")
                .param("name", "RussiaNew")
                .param("id", "100002"))
                .andDo(print())
                .andExpect(status().isFound());

        assertThat(restaurantService.getAll(), hasSize(5));
        assertThat(restaurantService.getAll(), hasItem(
                allOf(hasProperty("name", is("RussiaNew")))
        ));
    }
}
