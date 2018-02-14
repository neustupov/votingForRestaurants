package ru.neustupov.votingForRestaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id")
})
@Entity
@Table(name = "meals")
public class Meal extends AbstractNamedEntity {

    public static final String DELETE = "Meal.delete";

    @Column(name = "id_menu")
    @NotNull
    private Integer idMenu;

    @Column(name = "price")
    @NotNull
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menu", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Menu menu;

    public Meal() {
    }

    public Meal(Integer price) {
        this.price = price;
    }

    public Meal(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
