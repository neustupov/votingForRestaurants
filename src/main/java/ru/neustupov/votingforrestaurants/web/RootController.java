package ru.neustupov.votingforrestaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import ru.neustupov.votingforrestaurants.AuthorizedUser;
import ru.neustupov.votingforrestaurants.model.Meal;
import ru.neustupov.votingforrestaurants.model.Menu;
import ru.neustupov.votingforrestaurants.service.*;
import ru.neustupov.votingforrestaurants.to.UserTo;
import ru.neustupov.votingforrestaurants.util.UserUtil;
import ru.neustupov.votingforrestaurants.web.user.AbstractUserController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Controller
public class RootController extends AbstractUserController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/")
    public String root() {
        return "redirect:restaurants";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    public String menus() {
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

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "profile";
        } else {
            super.update(userTo, AuthorizedUser.id());
            AuthorizedUser.get().update(userTo);
            status.setComplete();
            if (AuthorizedUser.get().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                return "redirect:restaurants";
            } else {
                return "redirect:profileRestaurants";
            }
        }
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "profile";
        } else {
            super.create(UserUtil.createNewFromTo(userTo));
            status.setComplete();
            return "redirect:login?message=app.registered&username=" + userTo.getEmail();
        }
    }

    private int getId(HttpServletRequest request, String param) {
        String paramId = Objects.requireNonNull(request.getParameter(param));
        return Integer.valueOf(paramId);
    }
}
