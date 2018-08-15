package com.epam.ivanou.avia.model;

import javax.persistence.*;

/**
 * JavaBean class of Staff entity
 */
@Entity
@Table(name = "staff")
public class Staff extends AbstractBaseEntity {

    @Column(name = "st_Fname", nullable = false)
    private String firstName;

    @Column(name = "st_Lname", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "department", joinColumns = @JoinColumn(name = "st_id"))
    @Column(name = "st_id")
//    @ElementCollection(fetch = FetchType.EAGER)
    private Department department;

    public Staff() {
    }

    public Staff(Staff staff) {
        this.id = staff.getId();
        this.firstName = staff.getFirstName();
        this.lastName = staff.getLastName();
        this.department = staff.getDepartment();
    }

    public Staff(Integer id, String firstName, String lastName, Department department) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public Staff(String firstName, String lastName, Department department) {
        super(null);
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;

        Staff staff = (Staff) o;

        return getFirstName().equals(staff.getFirstName()) && getLastName().equals(staff.getLastName())
                && getDepartment() == staff.getDepartment();
    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getDepartment().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                '}';
    }
}
