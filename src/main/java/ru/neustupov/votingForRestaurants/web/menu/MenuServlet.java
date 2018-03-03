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
            case "all":
            default:
                Integer restId = Integer.parseInt(request.getParameter("id"));
                List<Menu> filteredMenus = menus.stream()
                        .filter(menu -> (menu.getRestId() == restId))
                        .collect(Collectors.toList());
                request.setAttribute("restId", restId);
                request.setAttribute("menusList", filteredMenus);
                request.getRequestDispatcher("/menus.jsp").forward(request, response);
                break;
        }
    }
}
