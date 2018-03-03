package ru.neustupov.votingForRestaurants.web.meal;

import org.slf4j.Logger;
import ru.neustupov.votingForRestaurants.model.Meal;
import ru.neustupov.votingForRestaurants.util.MockUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        List<Meal> meals = MockUtil.MEALS;

        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "all":
            default:
                Integer menuId = Integer.parseInt(request.getParameter("id"));
                Integer restId = Integer.parseInt(request.getParameter("restId"));
                List<Meal> filteredMeals = meals.stream()
                        .filter(meal -> (meal.getIdMenu() == menuId))
                        .collect(Collectors.toList());
                request.setAttribute("restId", restId);
                request.setAttribute("mealsList", filteredMeals);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }
}
