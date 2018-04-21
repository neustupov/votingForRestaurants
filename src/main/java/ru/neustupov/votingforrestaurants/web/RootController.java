package ru.neustupov.votingforrestaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.neustupov.votingforrestaurants.AuthorizedUser;
import ru.neustupov.votingforrestaurants.model.Meal;
import ru.neustupov.votingforrestaurants.model.Menu;
import ru.neustupov.votingforrestaurants.service.*;
import ru.neustupov.votingforrestaurants.util.RestaurantsUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Controller
public class RootController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MealService mealService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        return "redirect:/restaurants";
    }

    @GetMapping("/restaurants")
    public String restaurants(Model model) {
        model.addAttribute("restaurantsList",
                RestaurantsUtil.getWithVotes(restaurantService.getAll(), voteService.getAllForCurrentDate()));
        return "restaurants";
    }

    @GetMapping("/menus")
    public String menus(Model model, HttpServletRequest request) {
        model.addAttribute("menusList", menuService.getAll(getId(request,"restId")));
        model.addAttribute("restId", request.getParameter("restId"));
        return "menus";
    }

    @GetMapping("/getTodaysMenuWithMeals")
    public String getTodaysMenuWithMeals(HttpServletRequest request, Model model){
        Menu menu = menuService.getTodaysMenuWithMeals(getId(request, "restId"));
        Set<Meal> mealList;
        if(menu != null){
            mealList = menu.getMeals();
            model.addAttribute("menuId", menu.getId());
        }else{
            mealList = Collections.emptySet();
        }
        model.addAttribute("mealsList", mealList);
        model.addAttribute("restId", getId(request, "restId"));
        return "todays";
    }

    @GetMapping("/meals")
    public String meals(HttpServletRequest request, Model model){
        model.addAttribute("mealsList", mealService.getAll(getId(request, "menuId")));
        model.addAttribute("menuId", request.getParameter("menuId"));
        model.addAttribute("restId", request.getParameter("restId"));
        return "meals";
    }

    @GetMapping("/votes")
    public String votes(HttpServletRequest request, Model model){
        model.addAttribute("votesList", voteService.getAll());
        return "votes";
    }

    private int getId(HttpServletRequest request, String param) {
        String paramId = Objects.requireNonNull(request.getParameter(param));
        return Integer.valueOf(paramId);
    }
}
