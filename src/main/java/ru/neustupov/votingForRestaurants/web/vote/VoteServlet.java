package ru.neustupov.votingForRestaurants.web.vote;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.neustupov.votingForRestaurants.model.Vote;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class VoteServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private VoteRestController voteRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        voteRestController = springContext.getBean(VoteRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            Vote vote = new Vote(
                    Integer.parseInt(request.getParameter("id")),
                    LocalDateTime.parse(request.getParameter("dateTime")));

            if (request.getParameter("id").isEmpty()) {
                voteRestController.create(vote);
            } else {
                voteRestController.update(vote, getId(request));
            }
            response.sendRedirect("votes");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                voteRestController.delete(id);
                response.sendRedirect("votes");
                break;
            case "create":
            case "update":
                final Vote vote = "create".equals(action) ?
                        new Vote(getId(request), LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)) :
                        voteRestController.get(getId(request));
                request.setAttribute("vote", vote);
                request.getRequestDispatcher("/voteForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("votes", voteRestController.getAll());
                request.getRequestDispatcher("/votes.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
