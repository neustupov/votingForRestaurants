package ru.neustupov.votingForRestaurants.model;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id")
})
@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity {

    public static final String DELETE = "User.delete";

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String name;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 64)
    private String password;

    private boolean enabled = true;

    private Date registered = new Date();

    private Set<Role> roles;

    private Map<Integer, LocalDateTime> votes;

    public User() {
    }

    public User(String name, String password, boolean enabled, Date registered, Set<Role> roles) {
        this.name = name;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }

    public User(Integer id, String name, String password, boolean enabled, Date registered, Set<Role> roles) {
        super(id, name);
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Map<Integer, LocalDateTime> getVotes() {
        return votes;
    }

    public void setVotes(Map<Integer, LocalDateTime> votes) {
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
