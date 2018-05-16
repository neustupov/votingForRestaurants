package ru.neustupov.votingforrestaurants.web;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.service.MealService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.neustupov.votingforrestaurants.TestUtil.userHttpBasic;
import static ru.neustupov.votingforrestaurants.UserTestData.ADMIN;

public class MealControllerTest extends AbstractControllerTest {

    @Autowired
    private MealService mealService;

    @Test
    public void testMeals() throws Exception {
        mockMvc.perform(get("/meals")
                .with(userHttpBasic(ADMIN))
                .param("menuId", "100007"))
                .andDo(print())
                .andExpect(status().isFound())/*
                .andExpect(view().name("meals"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
                .andExpect(model().attribute("mealsList", hasSize(2)))
                .andExpect(model().attribute("mealsList", hasItem(
                        allOf(
                                hasProperty("name", is("Apple")),
                                hasProperty("price", is(5))
                        )
                )))*/;
    }

    //TODO understand what the problem is
    @Ignore
    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/ajax/admin/meals/100014")
                .with(userHttpBasic(ADMIN))
                .param("menuId", "100007"))
                .andDo(print())
                .andExpect(status().isFound());

        assertThat(mealService.getAll(100007), hasSize(1));
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(get("/meals/update")
                .with(userHttpBasic(ADMIN))
                .param("mealId", "100014")
                .param("menuId", "100007")
                .param("restId", "100002"))
                .andDo(print())
                .andExpect(status().isFound())/*
                .andExpect(view().name("mealForm"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealForm.jsp"))
                .andExpect(model().attribute("meal",
                        hasProperty("id", is(100014))
                ))
                .andExpect(model().attribute("meal",
                        hasProperty("name", is("Apple"))))
                .andExpect(model().attribute("menuId", "100007"))
                .andExpect(model().attribute("restId", "100002"))*/;
    }

    @Ignore
    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(post("/ajax/admin/meals")
                .with(userHttpBasic(ADMIN))
                .with(csrf())
                .param("menuId", "100007")
                .param("name", "NewMeal")
                .param("price", "100"))
                .andDo(print())
                .andExpect(status().isFound());

        assertThat(mealService.getAll(100007), hasSize(3));
        assertThat(mealService.getAll(100007), hasItem(
                allOf(hasProperty("name", is("NewMeal")))
        ));
    }

    //TODO understand what the problem is
    @Ignore
    @Test
    public void testCreateOrUpdateOnlyCreate() throws Exception {
        mockMvc.perform(post("/ajax/admin/meals")
                .with(userHttpBasic(ADMIN))
                .param("name", "AppleNew")
                .param("price", "15")
                .param("id", "")
                .param("menuId", "100007")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isFound());

        assertThat(mealService.getAll(100007), hasSize(3));
        assertThat(mealService.getAll(100007), hasItem(
                allOf(hasProperty("name", is("AppleNew")))
        ));
    }

    //TODO understand what the problem is
    @Ignore
    @Test
    public void testCreateOrUpdateOnlyUpdate() throws Exception {
        mockMvc.perform(post("/ajax/admin/meals/")
                .param("id", "100014")
                .param("name", "AppleNew")
                .param("price", "15")
                .param("menuId", "100007")
                .with(userHttpBasic(ADMIN))
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isFound());

        assertThat(mealService.getAll(100007), hasSize(2));
        assertThat(mealService.getAll(100007), hasItem(
                allOf(hasProperty("name", is("AppleNew")))
        ));
    }
}
