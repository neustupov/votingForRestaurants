package ru.neustupov.votingForRestaurants.web.vote;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class VoteServlet extends HttpServlet {

    private static final Logger log = getLogger(VoteServlet.class);

    private ConfigurableApplicationContext springContext;
    private RestaurantRestController restaurantRestController;
    private VoteRestController voteRestController;
    private AdminRestController adminRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        restaurantRestController = springContext.getBean(RestaurantRestController.class);
        voteRestController = springContext.getBean(VoteRestController.class);
        adminRestController = springContext.getBean(AdminRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = adminRestController.get(AuthorizedUser.id());

        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "create":
            case "update":
                Restaurant restaurant = restaurantRestController.get(
                        Integer.parseInt(request.getParameter("restId")));
                Vote vote = new Vote(user, LocalDateTime.now(), restaurant);
                voteRestController.create(vote);
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
