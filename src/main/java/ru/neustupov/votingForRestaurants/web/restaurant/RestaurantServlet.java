package ru.neustupov.votingForRestaurants.web.restaurant;

import org.slf4j.Logger;
import ru.neustupov.votingForRestaurants.AuthorizedUser;
import ru.neustupov.votingForRestaurants.model.Restaurant;
import ru.neustupov.votingForRestaurants.util.MockUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class RestaurantServlet extends HttpServlet{

    private static final Logger log = getLogger(RestaurantServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to restaurant");

        List<Restaurant> restaurants = MockUtil.RESTAURANTS;

        request.setAttribute("restaurantsList", restaurants);
        request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
    }
}
