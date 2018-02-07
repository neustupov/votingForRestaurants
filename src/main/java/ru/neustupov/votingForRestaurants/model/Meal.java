package ru.neustupov.votingForRestaurants.model;

public class Meal extends AbstractNamedEntity {

    private Integer price;

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
