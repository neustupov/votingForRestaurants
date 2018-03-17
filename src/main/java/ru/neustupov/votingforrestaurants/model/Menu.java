package ru.neustupov.votingforrestaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "menus")
public class Menu extends AbstractBaseEntity {

    @Column(name = "add_date", columnDefinition = "timestamp default now()",  nullable = false)
    @NotNull
    private LocalDateTime addDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    private Set<Meal> meals;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rest", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Menu() {
    }

    public Menu(Menu m) {
        this(m.getId(), m.getAddDate());
    }

    public Menu(@NotNull Integer id, @NotNull LocalDateTime addDate) {
        super(id);
        this.addDate = addDate;
    }

    public Menu(@NotNull LocalDateTime addDate) {
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /*@Override
    public String toString() {
        return "Menu{" +
                "restId=" +
                ", addDate=" + addDate +
                ", id=" + id +
                ", meals=" + meals +
                ", restaurant=" + restaurant +
                '}';
    }*/
}
