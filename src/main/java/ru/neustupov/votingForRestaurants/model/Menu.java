package ru.neustupov.votingForRestaurants.model;

import java.time.LocalDateTime;
import java.util.Set;

public class Menu {

    private LocalDateTime addDate;

    private Set<Meal> meals;

    public Menu() {
    }

    public Menu(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    public LocalDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }
}
