package pl.pp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by dpp on 12/17/16.
 */
@Entity (name = "Person")
@Table (name = "Person")
@SequenceGenerator ( name = "account_sequence", sequenceName = "account_sequence", allocationSize = 1)
public class Person implements Serializable {

    @Id
    @GeneratedValue (generator = "account_sequence", strategy = GenerationType.SEQUENCE)
    @Column (name = "id", nullable = false, updatable = false)
    private long id;

    @NotNull
    @Column (name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Size (min = 59, max = 60)
    @Column (name = "password", columnDefinition = "char 60, not null")
    private String password;

    @Column(name = "is_active", nullable = false)
    private boolean is_active;

    @Column(name = "birth_date")
    private LocalDate birth_date;

    public Person () {

    }

    public Person (String username, String password, boolean active) {
        this.username = username;
        this.password = password;
        this.is_active = active;
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

    public boolean isIs_active() {
        return is_active;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}
