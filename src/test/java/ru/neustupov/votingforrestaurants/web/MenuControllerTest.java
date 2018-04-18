package ru.neustupov.votingforrestaurants.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.service.MenuService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class MenuControllerTest extends AbstractControllerTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void testMenus() throws Exception {
        mockMvc.perform(get("/menus").param("restId", "100002"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("menus"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/menus.jsp"))
                .andExpect(model().attribute("menusList", hasSize(3)))
                .andExpect(model().attribute("menusList", hasItem(
                        allOf(
                                hasProperty("id", is(100007))
                        )
                )));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(get("/menus/delete")
                .param("menuId", "100007")
                .param("restId", "100002"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/menus?restId=100002"));

        assertThat(menuService.getAll(100002), hasSize(2));
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(get("/menus/create")
                .param("restId", "100002"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/menus?restId=100002"))
                .andExpect(model().attribute("restId", 100002));
    }

    @Test
    public void testGetTodaysMenuWithMeals() throws Exception {
        mockMvc.perform(get("/menus/getTodaysMenuWithMeals")
                .param("restId", "100002"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("todays"))
                .andExpect(model().attribute("mealsList", hasSize(1)));
    }
}
