package ru.neustupov.votingForRestaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @Column(name = "date_time", columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(User user, LocalDateTime dateTime) {
        this.user = user;
        this.dateTime = dateTime;
    }

    public Vote(Integer id, User user, LocalDateTime dateTime) {
        super(id);
        this.user = user;
        this.dateTime = dateTime;
    }

    public Vote(User user, LocalDateTime dateTime, Restaurant restaurant) {
        this.user = user;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public Vote(Integer id, User user, LocalDateTime dateTime, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
