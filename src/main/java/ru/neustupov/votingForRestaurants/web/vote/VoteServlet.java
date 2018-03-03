package ru.neustupov.votingForRestaurants.web.vote;

import ru.neustupov.votingForRestaurants.model.Restaurant;
import ru.neustupov.votingForRestaurants.model.Vote;
import ru.neustupov.votingForRestaurants.util.MockUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class VoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Restaurant> restaurantList = MockUtil.RESTAURANTS;

        String action = request.getParameter("action");

        int voteId = MockUtil.getCounterForVote();

        switch (action == null ? "all" : action) {
            case "create":
            case "update":
                Vote vote = new Vote(voteId, LocalDateTime.now());
                MockUtil.VOTES.add(vote);
                /*request.setAttribute("restaurantsList", restaurantList);
                request.getRequestDispatcher("/restaurants.jsp").forward(request, response);*/
                response.sendRedirect("restaurants");
                break;/*
            case "all":
            default:
                request.setAttribute("votes", voteRestController.getAll());
                request.getRequestDispatcher("/votes.jsp").forward(request, response);
                break;*/
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
