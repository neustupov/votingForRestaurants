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

    public Vote(Vote v) {
        this(v.getId(), v.getDateTime());
    }

    public Vote(@NotNull LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Vote(@NotNull User user, @NotNull LocalDateTime dateTime) {
        this.user = user;
        this.dateTime = dateTime;
    }

    public Vote(@NotNull Integer id, @NotNull LocalDateTime dateTime) {
        super(id);
        this.dateTime = dateTime;
    }

    public Vote(@NotNull Integer id, @NotNull User user, @NotNull LocalDateTime dateTime) {
        super(id);
        this.user = user;
        this.dateTime = dateTime;
    }

    public Vote(@NotNull User user, @NotNull LocalDateTime dateTime, @NotNull Restaurant restaurant) {
        this.user = user;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public Vote(@NotNull Integer id, @NotNull User user, @NotNull LocalDateTime dateTime, @NotNull Restaurant restaurant) {
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
