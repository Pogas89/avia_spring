package com.epam.ivanou.avia.model;

import javax.persistence.*;

/**
 * JavaBean class of User entity
 */
@Entity
@Table(name = "user")
public class User extends AbstractBaseEntity {

    @Column(name = "us_email", nullable = false)
    private String email;

    @Column(name = "us_password", nullable = false)
    private String password;

    @Column(name = "us_Fname", nullable = false)
    private String firstName;

    @Column(name = "us_Lname", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user_id")
    @ElementCollection(fetch = FetchType.EAGER)
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
