package ru.neustupov.votingforrestaurants.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.service.VoteService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.neustupov.votingforrestaurants.TestUtil.userAuth;
import static ru.neustupov.votingforrestaurants.UserTestData.ADMIN;
import static ru.neustupov.votingforrestaurants.UserTestData.USER;

public class VoteControllerTest extends AbstractControllerTest{

    @Autowired
    private VoteService voteService;

    @Test
    public void testVotes() throws Exception {
        mockMvc.perform(get("/votes")
                .with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/votes.jsp"));
    }

    @Test
    public void testVotesAccessDenied() throws Exception {
        mockMvc.perform(get("/votes")
                .with(userAuth(USER)))
                .andDo(print())
                .andExpect(forwardedUrl("/WEB-INF/jsp/exception/exception.jsp"));
    }

    @Test
    public void testUpdateOrCreateOnlyCreate() throws Exception {
        mockMvc.perform(post("/ajax/admin/votes")
                /*.param("restId", "100002")*/
                /*.with(userHttpBasic(ADMIN))*/
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isFound());
    }
}
