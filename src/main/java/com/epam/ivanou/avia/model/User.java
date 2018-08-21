package com.epam.ivanou.avia.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.EnumSet;
import java.util.Set;

/**
 * JavaBean class of User entity
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = User.GET_ALL, query = "SELECT u FROM User u LEFT JOIN FETCH u.role ORDER BY u.email"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.email=:email"),
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id")
})
public class User extends AbstractBaseEntity {

    public static final String DELETE = "User.delete";
    public static final String GET_ALL = "User.getAll";
    public static final String BY_EMAIL = "User.getByEmail";

    @Column(name = "email", nullable = false)
    @Email
    @NotEmpty
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty
    private String password;

    @Column(name = "firstname", nullable = false)
    @NotEmpty
    private String firstname;

    @Column(name = "lastname", nullable = false)
    @NotEmpty
    private String lastname;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> role;

    public User(){
    }

    public User(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.enabled = user.isEnabled();
        this.role = user.getRole();
    }

    public User(Integer id, @NotEmpty String email, @NotEmpty String password,
                @NotEmpty String firstname, @NotEmpty String lastname,
                boolean enabled, Set<Role> role) {
        super(id);
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.enabled = enabled;
        this.role = role;
    }

    public User(Integer id, @NotEmpty String email, @NotEmpty String password,
                @NotEmpty String firstname, @NotEmpty String lastname, Role role, Role... roles) {
        this(id, email, password, firstname, lastname, true, EnumSet.of(role, roles));
    }

    public User(@NotEmpty String email, @NotEmpty String password,
                @NotEmpty String firstname, @NotEmpty String lastname,
                boolean enabled, Set<Role> role) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.enabled = enabled;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        return isEnabled() == user.isEnabled() && getEmail().equals(user.getEmail())
                && getPassword().equals(user.getPassword())
                && getFirstname().equals(user.getFirstname())
                && getLastname().equals(user.getLastname())
                && getRole().equals(user.getRole());
    }

    @Override
    public int hashCode() {
        int result = getEmail().hashCode();
        result = (31 * result) + getPassword().hashCode();
        result = (31 * result) + getFirstname().hashCode();
        result = (31 * result) + getLastname().hashCode();
        result = (31 * result) + (isEnabled() ? 1 : 0);
        result = (31 * result) + getRole().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", enabled=" + enabled +
                ", role=" + role +
                '}';
    }
}
