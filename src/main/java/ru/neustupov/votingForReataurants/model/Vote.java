package ru.neustupov.votingForReataurants.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity {

    private String user;

    private LocalDateTime dateTime;

    public Vote() {
    }

    public Vote(String user, LocalDateTime dateTime) {
        this.user = user;
        this.dateTime = dateTime;
    }

    public Vote(Integer id, String user, LocalDateTime dateTime) {
        super(id);
        this.user = user;
        this.dateTime = dateTime;
    }
}
