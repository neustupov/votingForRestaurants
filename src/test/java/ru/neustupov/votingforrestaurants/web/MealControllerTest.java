package ru.neustupov.votingforrestaurants.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.service.MealService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class MealControllerTest extends AbstractControllerTest {

    @Autowired
    private MealService mealService;

    @Test
    public void testMeals() throws Exception {
        mockMvc.perform(get("/meals")
                .param("menuId", "100007"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meals"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
                .andExpect(model().attribute("mealsList", hasSize(2)))
                .andExpect(model().attribute("mealsList", hasItem(
                        allOf(
                                hasProperty("name", is("Apple")),
                                hasProperty("price", is(5))
                        )
                )));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(get("/meals/delete")
                .param("mealId", "100014")
                .param("menuId", "100007"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/meals?menuId=100007"));

        assertThat(mealService.getAll(100007), hasSize(1));
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(get("/meals/update")
                .param("mealId", "100014")
                .param("menuId", "100007")
                .param("restId", "100002"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealForm"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealForm.jsp"))
                .andExpect(model().attribute("meal",
                        hasProperty("id", is(100014))
                ))
                .andExpect(model().attribute("meal",
                        hasProperty("name", is("Apple"))))
                .andExpect(model().attribute("menuId", "100007"))
                .andExpect(model().attribute("restId", "100002"));
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(get("/meals/create")
                .param("menuId", "100007")
                .param("restId", "100002"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealForm"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealForm.jsp"))
                .andExpect(model().attribute("meal",
                        hasProperty("id", isEmptyOrNullString())))
                .andExpect(model().attribute("meal",
                        hasProperty("name", isEmptyOrNullString())))
                .andExpect(model().attribute("meal",
                        hasProperty("price", isEmptyOrNullString())))
                .andExpect(model().attribute("menuId", "100007"))
                .andExpect(model().attribute("restId", "100002"));
    }

    @Test
    public void testCreateOrUpdateOnlyCreate() throws Exception {
        mockMvc.perform(post("/meals")
                .param("name", "AppleNew")
                .param("price", "15")
                .param("mealId", "")
                .param("menuId", "100007")
                .param("restId", "100002"))
                .andDo(print())
                .andExpect(status().isFound());

        assertThat(mealService.getAll(100007), hasSize(3));
        assertThat(mealService.getAll(100007), hasItem(
                allOf(hasProperty("name", is("AppleNew")))
        ));
    }

    @Test
    public void testCreateOrUpdateOnlyUpdate() throws Exception {
        mockMvc.perform(post("/meals")
                .param("name", "AppleNew")
                .param("price", "15")
                .param("mealId", "100014")
                .param("menuId", "100007")
                .param("restId", "100002"))
                .andDo(print())
                .andExpect(status().isFound());

        assertThat(mealService.getAll(100007), hasSize(2));
        assertThat(mealService.getAll(100007), hasItem(
                allOf(hasProperty("name", is("AppleNew")))
        ));
    }
}
