package ru.neustupov.votingForRestaurants.web;

import org.slf4j.Logger;
import ru.neustupov.votingForRestaurants.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        List<Meal> meals = Arrays.asList(
                new Meal(1, "Soup", 1, 100),
                new Meal(2, "Pasta", 1, 150),
                new Meal(3, "Sausages", 1, 200),
                new Meal(4, "Juice", 1, 50)
        );

        request.setAttribute("mealsList", meals);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
