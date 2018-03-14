package ru.neustupov.votingforrestaurants.web.meal;

import org.slf4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.neustupov.votingforrestaurants.model.Meal;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    private MealRestController restController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        restController = ctx.getBean(MealRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        int restId = Integer.parseInt(request.getParameter("restId"));
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        if (action == null) {
            Meal meal = new Meal(
                    request.getParameter("name"),
                    Integer.parseInt(request.getParameter("price")));

            if (request.getParameter("mealId").isEmpty()) {
                restController.create(meal, menuId);
            } else {
                restController.update(meal, Integer.parseInt(request.getParameter("mealId")), menuId);
            }
            request.setAttribute("menuId", menuId);
            request.setAttribute("restId", restId);
            request.setAttribute("mealsList", restController.getAll(Integer.parseInt(request.getParameter("menuId"))));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int mealId = Integer.parseInt(request.getParameter("mealId"));
                restController.delete(mealId, Integer.parseInt(request.getParameter("menuId")));
                request.setAttribute("restId", Integer.parseInt(request.getParameter("restId")));
                request.setAttribute("menuId", Integer.parseInt(request.getParameter("menuId")));
                request.setAttribute("mealsList", restController.getAll(Integer.parseInt(request.getParameter("menuId"))));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal() :
                        restController.get(Integer.parseInt(request.getParameter("mealId")),
                                Integer.parseInt(request.getParameter("menuId")));
                request.setAttribute("meal", meal);
                request.setAttribute("menuId", Integer.parseInt(request.getParameter("menuId")));
                request.setAttribute("restId", Integer.parseInt(request.getParameter("restId")));
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                Integer restId = Integer.parseInt(request.getParameter("restId"));
                List<Meal> meals = restController.getAll(Integer.parseInt(request.getParameter("menuId")));
                request.setAttribute("restId", restId);
                request.setAttribute("menuId", Integer.parseInt(request.getParameter("menuId")));
                request.setAttribute("mealsList", meals);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }
}
