package ru.neustupov.votingforrestaurants.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.neustupov.votingforrestaurants.TestUtil;
import ru.neustupov.votingforrestaurants.model.User;
import ru.neustupov.votingforrestaurants.to.UserTo;
import ru.neustupov.votingforrestaurants.util.UserUtil;
import ru.neustupov.votingforrestaurants.web.AbstractControllerTest;
import ru.neustupov.votingforrestaurants.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.neustupov.votingforrestaurants.TestUtil.userHttpBasic;
import static ru.neustupov.votingforrestaurants.UserTestData.*;
import static ru.neustupov.votingforrestaurants.web.user.ProfileRestController.REST_URL;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Test
    void testGet() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(USER)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(USER))
        );
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), ADMIN);
    }

    @Test
    void testUpdate() throws Exception {
        UserTo updatedTo = new UserTo(null, "newName", "user@yandex.ru","newPassword");
        mockMvc.perform(put(REST_URL)
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isOk());

        assertMatch(userService.getByEmail("user@yandex.ru"), UserUtil.updateFromTo(new User(USER), updatedTo));
    }

    @Test
    void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }
}
