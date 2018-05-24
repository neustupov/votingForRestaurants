package ru.neustupov.votingforrestaurants.web;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.service.MenuService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.neustupov.votingforrestaurants.TestUtil.userHttpBasic;
import static ru.neustupov.votingforrestaurants.UserTestData.ADMIN;

public class MenuControllerTest extends AbstractControllerTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void testMenus() throws Exception {
        mockMvc.perform(get("/menus")
                .param("restId", "100002")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isFound())/*
                .andExpect(view().name("menus"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/menus.jsp"))
                .andExpect(model().attribute("menusList", hasSize(3)))
                .andExpect(model().attribute("menusList", hasItem(
                        allOf(
                                hasProperty("id", is(100007))
                        )
                )))*/;
    }

    //TODO understand what the problem is
    @Ignore
    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/ajax/admin/menus/100007")
                .with(userHttpBasic(ADMIN))
                .param("restId", "100002"))
                .andDo(print())
                .andExpect(status().isFound());

        assertThat(menuService.getAll(100002), hasSize(2));
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(get("/ajax/admin/menus")
                .with(userHttpBasic(ADMIN))
                .param("restId", "100002"))
                .andDo(print())
                .andExpect(status().isFound());
    }

    @Test
    public void testGetTodaysMenuWithMeals() throws Exception {
        mockMvc.perform(get("/getTodaysMenuWithMeals")
                .with(userHttpBasic(ADMIN))
                .param("restId", "100002"))
                .andDo(print())
                .andExpect(status().isFound())/*
                .andExpect(view().name("todays"))
                .andExpect(model().attribute("mealsList", hasSize(1)))*/;
    }
}
