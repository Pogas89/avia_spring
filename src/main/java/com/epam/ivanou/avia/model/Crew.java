package com.epam.ivanou.avia.model;

import javax.persistence.*;
import java.util.List;

/**
 * JavaBean class of Crew entity
 */
@Entity
@Table(name = "crews")
@NamedQueries({
        @NamedQuery(name = Crew.GET_ALL, query = "select c from Crew c"),
        @NamedQuery(name = Crew.DELETE, query = "DELETE FROM Crew c WHERE c.id=?1")
})
public class Crew extends AbstractBaseEntity {

    public static final String DELETE = "Crew.delete";
    public static final String GET_ALL = "Crew.getAll";

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "crew_staff",
            joinColumns = @JoinColumn(name = "cr_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "st_id", referencedColumnName = "id")
    )
    private List<Staff> staffs;

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

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Crew)) {
            return false;
        }

        Crew crew = (Crew) o;

        return getName().equals(crew.getName()) && getUser().equals(crew.getUser());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = (31 * result) + getUser().hashCode();
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
