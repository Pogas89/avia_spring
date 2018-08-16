package com.epam.ivanou.avia.model;

import javax.persistence.*;

/**
 * JavaBean class of Crew entity
 */
@Entity
@Table(name = "crews")
public class Crew extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Crew() {
    }

    public Crew(Crew crew) {
        this.id = crew.getId();
        this.name = crew.getName();
        this.user = crew.getUser();
    }

    public Crew(Integer id, String name, User user) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Crew)) return false;

        Crew crew = (Crew) o;

        return getName().equals(crew.getName()) && getUser().equals(crew.getUser());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getUser().hashCode();
        return result;
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
