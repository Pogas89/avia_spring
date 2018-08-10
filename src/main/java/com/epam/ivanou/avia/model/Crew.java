package com.epam.ivanou.avia.model;

/**
 * JavaBean class of Crew entity
 */
public class Crew extends AbstractBaseEntity {
    private String name;

    private Integer userId;

    public Crew() {
    }

    public Crew(Crew crew) {
        this.id = crew.getId();
        this.name = crew.getName();
        this.userId = crew.getUserId();
    }

    public Crew(Integer id, String name, Integer userId) {
        super(id);
        this.name = name;
        this.userId = userId;
    }

    public Crew(String name, Integer userId) {
        super(null);
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }
}
