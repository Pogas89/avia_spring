package com.epam.ivanou.avia.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * JavaBean class of User entity
 */
@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = User.All, query = "SELECT u FROM User u LEFT JOIN FETCH u.role ORDER BY u.email"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.email=:email"),
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=?1")
})
public class User extends AbstractBaseEntity {

    public static final String DELETE = "User.delete";
    public static final String All = "User.getAll";
    public static final String BY_EMAIL = "User.getByEmail";

    @Column(name = "us_email", nullable = false)
    @NotEmpty
    private String email;

    @Column(name = "us_password", nullable = false)
    @NotEmpty
    private String password;

    @Column(name = "us_Fname", nullable = false)
    @NotEmpty
    private String firstName;

    @Column(name = "us_Lname", nullable = false)
    @NotEmpty
    private String lastName;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user_id")
//    @ElementCollection(fetch = FetchType.EAGER)
    private Role role;

    public User(){
    }

    public User(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
    }

    public User(Integer id, String email, String password,
                String firstName, String lastName,Role role) {
        super(id);
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role=role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;

        User user = (User) obj;

        return (getEmail() != null ? getEmail().equals(user.getEmail()) : user.getEmail() == null)
                && (getPassword() != null ? getPassword().equals(user.getPassword()) : user.getPassword() == null)
                && (getFirstName() != null ? getFirstName().equals(user.getFirstName()) : user.getFirstName() == null)
                && (getLastName() != null ? getLastName().equals(user.getLastName()) : user.getLastName() == null);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }
}
