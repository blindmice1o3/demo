package com.example.demo.service.payroll.repository;

import com.example.demo.service.payroll.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// "This interface extends Spring Data JPA’s [JpaRepository], specifying the domain type as
// [Employee] and the id type as [Long]. This interface, though empty on the surface, packs a
// punch given it supports: creating new instances, updating existing ones, deleting, and
// finding (one, all, by simple or complex properties)."

// "Spring Data's repository solution makes it possible to sidestep data store specifics and instead
// solve a majority of problems using domain-specific terminology."

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
