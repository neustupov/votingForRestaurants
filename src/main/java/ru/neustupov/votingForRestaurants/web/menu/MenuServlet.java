package ru.neustupov.votingForRestaurants.web.menu;

import org.slf4j.Logger;
import ru.neustupov.votingForRestaurants.model.Menu;
import ru.neustupov.votingForRestaurants.util.MockUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MenuServlet extends HttpServlet{

    private static final Logger log = getLogger(MenuServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to menu");

        List<Menu> menus = MockUtil.MENUS;

        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            /*case "delete":
                int id = getId(request);
                mealController.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealController.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;*/
            case "all":
            default:
                Integer restId = Integer.parseInt(request.getParameter("id"));
                List<Menu> filteredMenus = menus.stream()
                        .filter(menu -> (menu.getRestId() == restId))
                        .collect(Collectors.toList());
                request.setAttribute("menusList", filteredMenus);
                request.getRequestDispatcher("/menus.jsp").forward(request, response);
                break;
        }
    }
}
