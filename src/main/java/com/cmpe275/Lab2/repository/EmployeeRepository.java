package com.cmpe275.Lab2.repository;
import com.cmpe275.Lab2.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
