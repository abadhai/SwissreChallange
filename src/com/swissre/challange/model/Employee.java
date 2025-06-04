package com.swissre.challange.model;

import java.util.Objects;

/**
 * Represents an employee with basic attributes.
 */
public class Employee {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final double salary;
    private final Integer managerId;

    public Employee(int id, String firstName, String lastName, double salary, Integer managerId) {
        this.id = id;
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.salary = salary;
        this.managerId = managerId;
    }

    public int getId() { return id; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public double getSalary() { return salary; }

    public Integer getManagerId() { return managerId; }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}