package com.epam.ivanou.avia.model;

/**
 * JavaBean class of Crew entity
 */
public class Crew extends AbstractBaseEntity {
    private String name;

    private User user;

    public Crew() {
    }

    public Crew(Crew crew) {
        this.id = crew.getId();
        this.name = crew.getName();
        this.user = crew.getUser();
    }

    public Crew(Integer id, String name, Integer userId) {
        super(id);
        this.name = name;
        this.user = user;
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

    @Override
    public String toString() {
        return "Crew{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
