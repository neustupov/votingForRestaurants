package ru.neustupov.votingforrestaurants.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.neustupov.votingforrestaurants.model.Meal;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController {

    @GetMapping
    public String meals(Model model, HttpServletRequest request) {
        model.addAttribute("mealsList", super.getAll(getId(request, "menuId")));
        model.addAttribute("menuId", request.getParameter("menuId"));
        model.addAttribute("restId", request.getParameter("restId"));
        return "meals";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request, Model model) {
        super.delete(getId(request, "mealId"), getId(request, "menuId"));
        model.addAttribute("menuId", request.getParameter("menuId"));
        model.addAttribute("restId", request.getParameter("restId"));
        return "redirect:/meals";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("meal", super.get(getId(request, "mealId"), getId(request, "menuId")));
        model.addAttribute("menuId", request.getParameter("menuId"));
        model.addAttribute("restId", request.getParameter("restId"));
        return "mealForm";
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        model.addAttribute("meal", new Meal());
        model.addAttribute("menuId", request.getParameter("menuId"));
        model.addAttribute("restId", request.getParameter("restId"));
        return "mealForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request, Model model) {
        Meal meal = new Meal(request.getParameter("name"), Integer.parseInt(request.getParameter("price")));

        if (request.getParameter("mealId").isEmpty()) {
            super.create(meal, getId(request, "menuId"));
        } else {
            super.update(getId(request, "id"), meal, getId(request, "mealId"),
                    getId(request, "menuId"));
        }

        model.addAttribute("menuId", getId(request, "menuId"));
        model.addAttribute("restId", request.getParameter("restId"));
        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request, String param) {
        String paramId = Objects.requireNonNull(request.getParameter(param));
        return Integer.valueOf(paramId);
    }
}
