package ru.neustupov.votingForReataurants.model;

import java.time.LocalDateTime;

public class Menu extends AbstractBaseEntity {

    private LocalDateTime addDate;

    public Menu() {
    }

    public Menu(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    public Menu(Integer id, LocalDateTime addDate) {
        super(id);
        this.addDate = addDate;
    }

    public LocalDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }
}
