package com.example.demo.service.payroll.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

// "With this domain object definition, we can now turn to [Spring Data JPA] to handle the tedious
// database interactions. Spring Data repositories are interfaces with methods supporting
// reading, updating, deleting, and creating records against a back end data store. Some
// repositories also support data paging, and sorting, where appropriate. Spring Data
// synthesizes implementations based on conventions found in the naming of the methods in
// the interface."

// "@Entity is a JPA annotation to make this object ready for storage in a JPA-based data store."
@Entity
public class Employee {
    // "JPA annotations to indicate it’s the primary key and automatically populated by the JPA provider."
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String role;

    public Employee() {}
    // "A custom constructor is created when we need to create a new instance, but don’t yet have an id."
    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
