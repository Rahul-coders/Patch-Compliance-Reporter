package com.internship.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.tool.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}