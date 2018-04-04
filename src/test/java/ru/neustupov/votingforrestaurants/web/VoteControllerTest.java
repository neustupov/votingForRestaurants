package ru.neustupov.votingforrestaurants.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neustupov.votingforrestaurants.service.VoteService;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class VoteControllerTest extends AbstractControllerTest{

    @Autowired
    private VoteService voteService;

    @Test
    public void testVotes() throws Exception {
        mockMvc.perform(get("/votes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("votes"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/votes.jsp"))
                .andExpect(model().attribute("votesList", hasSize(6)));
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(get("/votes/create")
                .param("restId", "100002"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/restaurants"))
                .andExpect(model().attribute("vote",
                        hasProperty("id", isEmptyOrNullString())));
    }
}
