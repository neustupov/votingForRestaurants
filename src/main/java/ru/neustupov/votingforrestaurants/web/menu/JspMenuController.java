package ru.neustupov.votingforrestaurants.web.menu;

import org.hsqldb.lib.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.neustupov.votingforrestaurants.model.Meal;
import ru.neustupov.votingforrestaurants.model.Menu;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping(value = "/menus")
public class JspMenuController extends AbstractMenuController{

    @GetMapping
    public String menus(Model model, HttpServletRequest request) {
        model.addAttribute("menusList", super.getAll(getId(request,"restId")));
        model.addAttribute("restId", request.getParameter("restId"));
        return "menus";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request, Model model) {
        super.delete(getId(request, "menuId"), getId(request, "restId"));
        model.addAttribute("restId", getId(request, "restId"));
        return "redirect:/menus";
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request, Model model) {
        super.create(new Menu(Date.valueOf(LocalDate.now())), getId(request, "restId"));
        model.addAttribute("restId", getId(request, "restId"));
        return "redirect:/menus";
    }

    @GetMapping("/getTodaysMenuWithMeals")
    public String getTodaysMenuWithMeals(HttpServletRequest request, Model model){
        Menu menu = super.getTodaysMenuWithMeals(getId(request, "restId"));
        Set<Meal> mealList;
        if(menu != null){
            mealList = menu.getMeals();
            model.addAttribute("menuId", menu.getId());
        }else{
            mealList = Collections.emptySet();
        }
        model.addAttribute("mealsList", mealList);
        model.addAttribute("restId", getId(request, "restId"));
        return "meals";
    }

    private int getId(HttpServletRequest request, String param) {
        String paramId = Objects.requireNonNull(request.getParameter(param));
        return Integer.valueOf(paramId);
    }
}
