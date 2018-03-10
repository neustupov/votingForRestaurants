package ru.neustupov.votingForRestaurants.web.vote;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.neustupov.votingForRestaurants.AuthorizedUser;
import ru.neustupov.votingForRestaurants.model.Restaurant;
import ru.neustupov.votingForRestaurants.model.User;
import ru.neustupov.votingForRestaurants.model.Vote;
import ru.neustupov.votingForRestaurants.web.restaurant.RestaurantRestController;
import ru.neustupov.votingForRestaurants.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class VoteServlet extends HttpServlet {

    private RestaurantRestController restaurantRestController;
    private VoteRestController voteRestController;
    private AdminRestController adminRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        restaurantRestController = ctx.getBean(RestaurantRestController.class);
        voteRestController = ctx.getBean(VoteRestController.class);
        adminRestController = ctx.getBean(AdminRestController.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "create":
            case "update":
                User user = adminRestController.get(AuthorizedUser.id());
                Restaurant restaurant = restaurantRestController.get(
                        Integer.parseInt(request.getParameter("restId")));
                Vote vote = new Vote(user, LocalDateTime.now(), restaurant);
                voteRestController.create(vote);
                response.sendRedirect("restaurants");
                break;
            case "all":
            default:
                request.setAttribute("votesList", voteRestController.getAll());
                request.getRequestDispatcher("/votes.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
