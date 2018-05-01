package ru.neustupov.votingforrestaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.neustupov.votingforrestaurants.model.Meal;
import ru.neustupov.votingforrestaurants.model.Menu;
import ru.neustupov.votingforrestaurants.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Controller
public class RootController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/")
    public String root() {
        return "redirect:restaurants";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/restaurants")
    public String restaurants() {
        return "restaurants";
    }

    @GetMapping("/profileRestaurants")
    public String profileRestaurants() {
        return "profileRestaurants";
    }

    @GetMapping("/menus")
    public String menus(Model model, HttpServletRequest request) {
        return "menus";
    }

    @GetMapping("/getTodaysMenuWithMeals")
    public String getTodaysMenuWithMeals(HttpServletRequest request, Model model) {
        Menu menu = menuService.getTodaysMenuWithMeals(getId(request, "restId"));
        Set<Meal> mealList;
        if (menu != null) {
            mealList = menu.getMeals();
            model.addAttribute("menuId", menu.getId());
        } else {
            mealList = Collections.emptySet();
        }
        model.addAttribute("mealsList", mealList);
        model.addAttribute("restId", getId(request, "restId"));
        return "todays";
    }

    @GetMapping("/getProfileTodaysMenuWithMeals")
    public String getProfileTodaysMenuWithMeals(HttpServletRequest request, Model model) {
        Menu menu = menuService.getTodaysMenuWithMeals(getId(request, "restId"));
        Set<Meal> mealList;
        if (menu != null) {
            mealList = menu.getMeals();
            model.addAttribute("menuId", menu.getId());
        } else {
            mealList = Collections.emptySet();
        }
        model.addAttribute("mealsList", mealList);
        model.addAttribute("restId", getId(request, "restId"));
        return "profileTodays";
    }

    @GetMapping("/meals")
    public String meals() {
        return "meals";
    }

    @GetMapping("/votes")
    public String votes() {
        return "votes";
    }

    private int getId(HttpServletRequest request, String param) {
        String paramId = Objects.requireNonNull(request.getParameter(param));
        return Integer.valueOf(paramId);
    }
}
