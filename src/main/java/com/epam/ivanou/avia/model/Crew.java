package com.epam.ivanou.avia.model;

/**
 * JavaBean class of Crew entity
 */
public class Crew {
    private Integer id;

    private String name;

    private User user;

    public Crew(Integer id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public Crew(String name, User user) {
        this(null,name, user);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isNew(){
        return id==null;
    }
}
