package com.epam.ivanou.avia.model;

/**
 * JavaBean class of Staff entity
 */
public class Staff {
    private Integer id;

    private String firstName;

    private String lastName;

    private Department department;

    public Staff(Integer id, String firstName, String lastName, Department department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public Staff(String firstName, String lastName, Department department) {
        this(null,firstName,lastName,department);
    }
}
