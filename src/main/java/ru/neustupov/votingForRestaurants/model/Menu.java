package ru.neustupov.votingForRestaurants.model;

import java.time.LocalDateTime;
import java.util.Set;

public class Menu extends AbstractBaseEntity{

    private Integer idRest;

    private LocalDateTime addDate;

    private Integer mealID;

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

    public Integer getIdRest() {
        return idRest;
    }

    public void setIdRest(Integer id_rest) {
        this.idRest = idRest;
    }

    public Integer getMealID() {
        return mealID;
    }

    public void setMealID(Integer mealID) {
        this.mealID = mealID;
    }
}
