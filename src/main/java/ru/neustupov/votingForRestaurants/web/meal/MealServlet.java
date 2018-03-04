package ru.neustupov.votingForRestaurants.web.meal;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.neustupov.votingForRestaurants.model.Meal;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    private ConfigurableApplicationContext springContext;
    private MealRestController restController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        restController = springContext.getBean(MealRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "all":
            default:
                Integer restId = Integer.parseInt(request.getParameter("restId"));
                Integer menuId = Integer.parseInt(request.getParameter("id"));
                List<Meal> meals = restController.getAll(menuId);
                request.setAttribute("restId", restId);
                request.setAttribute("mealsList", meals);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }
}
