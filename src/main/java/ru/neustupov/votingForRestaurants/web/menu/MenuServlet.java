package ru.neustupov.votingForRestaurants.web.menu;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.neustupov.votingForRestaurants.model.Menu;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MenuServlet extends HttpServlet{

    private static final Logger log = getLogger(MenuServlet.class);

    private ConfigurableApplicationContext springContext;
    private MenuRestController restController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        restController = springContext.getBean(MenuRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to menu");

        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "all":
            default:
                Integer restId = Integer.parseInt(request.getParameter("id"));
                List<Menu> menus = restController.getAll(restId);
                request.setAttribute("restId", restId);
                request.setAttribute("menusList", menus);
                request.getRequestDispatcher("/menus.jsp").forward(request, response);
                break;
        }
    }
}
