package ru.neustupov.votingForRestaurants.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Menu> menus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Vote> votes;

    public Restaurant() {
        super(null, null);
    }

    public Restaurant(String name){
        super(null, name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "menus=" + menus +
                ", votes=" + votes +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
