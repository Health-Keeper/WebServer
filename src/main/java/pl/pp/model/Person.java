package pl.pp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by dpp on 12/17/16.
 */
@Entity (name = "person")
@Table (name = "Person")
@SequenceGenerator ( name = "account_sequence", sequenceName = "account_sequence", allocationSize = 1)
public class Person implements Serializable {

    @Id
    @GeneratedValue (generator = "account_sequence", strategy = GenerationType.SEQUENCE)
    @Column (name = "account_id", nullable = false, updatable = false)
    private long id;

    @NotNull
    @Column (name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Size (min = 59, max = 60)
    @Column (name = "password", columnDefinition = "char 60, not null")
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active;

    public Person () {

    }

    public Person (String username, String password, boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
