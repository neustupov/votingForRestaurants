package ru.neustupov.votingForRestaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "meals")
public class Meal extends AbstractNamedEntity {

    @Column(name = "id_menu")
    @NotNull
    private Integer menuId;

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

    public Meal(Meal m){
        this(m.getId(), m.getMenuId(), m.getName(), m.getPrice());
    }

    public Meal(@NotNull Integer menuId, @NotNull Integer price) {
        this.menuId = menuId;
        this.price = price;
    }

    public Meal(Integer menuId, String name, Integer price){
        super(null, name);
        this.menuId = menuId;
        this.price = price;
    }

    public Meal(Integer id, @NotNull Integer menuId, String name, @NotNull Integer price) {
        super(id, name);
        this.menuId = menuId;
        this.price = price;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
