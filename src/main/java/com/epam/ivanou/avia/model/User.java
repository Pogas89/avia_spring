package com.epam.ivanou.avia.model;

/**
 * JavaBean class of User entity
 */
public class User {
    private Integer id;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private UserRole userRole;

    public User(Integer id, String login, String password,
                String firstName, String lastName, String email, UserRole userRole) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRole = userRole;
    }

    public User(String login, String password, String firstName,
                String lastName, String email, UserRole userRole) {
        this(null,login,password,firstName,lastName,email,userRole);
    }
}
