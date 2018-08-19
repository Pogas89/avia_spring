package com.epam.ivanou.avia.model;

import javax.persistence.*;

/**
 * JavaBean class of Staff entity
 */
@Entity
@Table(name = "staffs")
@NamedQueries({
        @NamedQuery(name = Staff.All, query = "select s from Staff s order by s.lastname, s.firstname"),
        @NamedQuery(name = Staff.DELETE, query = "delete from Staff s where s.id=?1"),
        @NamedQuery(name = Staff.BY_LASTNAME, query = "select s from Staff s where s.lastname=?1")
})
public class Staff extends AbstractBaseEntity {

    public static final String DELETE = "Staff.delete";
    public static final String All = "Staff.getAll";
    public static final String BY_LASTNAME = "Staff.getByLastName";

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "department")
    @Enumerated(EnumType.ORDINAL)
    private Department department;

    public Staff() {
    }

    public Staff(Staff staff) {
        this.id = staff.getId();
        this.firstname = staff.getFirstname();
        this.lastname = staff.getLastname();
        this.department = staff.getDepartment();
    }

    public Staff(Integer id, String firstname, String lastName, Department department) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastName;
        this.department = department;
    }

    public Staff(String firstname, String lastName, Department department) {
        super(null);
        this.firstname = firstname;
        this.lastname = lastName;
        this.department = department;
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

        return getFirstname().equals(staff.getFirstname()) && getLastname().equals(staff.getLastname())
                && getDepartment() == staff.getDepartment();
    }

    @Override
    public int hashCode() {
        int result = getFirstname().hashCode();
        result = 31 * result + getLastname().hashCode();
        result = 31 * result + getDepartment().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", department=" + department +
                '}';
    }
}
