package ru.neustupov.votingForRestaurants.web.restaurant;

import org.slf4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.neustupov.votingForRestaurants.model.Restaurant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class RestaurantServlet extends HttpServlet {

    private static final Logger log = getLogger(RestaurantServlet.class);

    private RestaurantRestController restController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        restController = ctx.getBean(RestaurantRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            Restaurant restaurant = new Restaurant(request.getParameter("name"));

            if (request.getParameter("restId").isEmpty()) {
                restController.create(restaurant);
            } else {
                restController.update(restaurant, getId(request));
            }
            response.sendRedirect("restaurants");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to restaurant");

        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int restIdd = Integer.parseInt(request.getParameter("restId"));
                restController.delete(restIdd);
                response.sendRedirect("restaurants");
                break;
            case "create":
            case "update":
                final Restaurant restaurant = "create".equals(action) ?
                        new Restaurant() :
                        restController.get(getId(request));
                request.setAttribute("restaurant", restaurant);
                request.getRequestDispatcher("/restaurantForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("restaurantsList", restController.getAll());
                request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("restId"));
        return Integer.parseInt(paramId);
    }
}
