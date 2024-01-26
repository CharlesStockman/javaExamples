package com.seleniumexpress.employeeapp.employeeservice.repository;

import com.seleniumexpress.employeeapp.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
EmployeeRepository extends JpaRepository<Employee, Integer> {
}
