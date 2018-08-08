package com.epam.ivanou.avia.model;

/**
 * JavaBean class of Staff entity
 */
public class Staff extends AbstractBaseEntity {
    private String firstName;

    private String lastName;

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
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                '}';
    }
}
