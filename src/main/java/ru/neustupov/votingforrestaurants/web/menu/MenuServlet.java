package ru.neustupov.votingforrestaurants.web.menu;

import org.slf4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.neustupov.votingforrestaurants.model.Menu;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class MenuServlet extends HttpServlet {

    private static final Logger log = getLogger(MenuServlet.class);

    private MenuRestController restController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        restController = ctx.getBean(MenuRestController.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to menu");

        String action = request.getParameter("action");
        int restId = Integer.parseInt(request.getParameter("restId"));

        switch (action == null ? "all" : action) {
            case "delete":
                int menuId = Integer.parseInt(request.getParameter("menuId"));
                restController.delete(menuId, restId);
                request.setAttribute("restId", restId);
                request.setAttribute("menusList", restController.getAll(restId));
                request.getRequestDispatcher("/menus.jsp").forward(request, response);
                break;
            case "create":
                final Menu menu = new Menu(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
                restController.create(menu, restId);
                request.setAttribute("restId", restId);
                request.setAttribute("menusList", restController.getAll(restId));
                request.getRequestDispatcher("/menus.jsp").forward(request, response);
                break;
            case "update":
                int id = Integer.parseInt(request.getParameter("id"));
                break;
            case "all":
            default:
                request.setAttribute("restId", restId);
                request.setAttribute("menusList", restController.getAll(restId));
                request.getRequestDispatcher("/menus.jsp").forward(request, response);
                break;
        }
    }
}
