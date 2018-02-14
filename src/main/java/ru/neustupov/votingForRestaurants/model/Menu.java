package ru.neustupov.votingForRestaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id")
})
@Entity
@Table(name = "menus")
public class Menu extends AbstractBaseEntity{

    public static final String DELETE = "Menu.delete";

    @Column(name = "id_rest")
    @NotNull
    private Integer idRest;

    @Column(name = "add_date", columnDefinition = "timestamp default now()")
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
}
