package ru.neustupov.votingForReataurants.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class Restaurant extends AbstractNamedEntity {

    private Set<Menu> menu;

    private Map<Integer, LocalDateTime> votes;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Set<Menu> getMenu() {
        return menu;
    }

    public void setMenu(Set<Menu> menu) {
        this.menu = menu;
    }

    public Map<Integer, LocalDateTime> getVotes() {
        return votes;
    }

    public void setVotes(Map<Integer, LocalDateTime> votes) {
        this.votes = votes;
    }
}
