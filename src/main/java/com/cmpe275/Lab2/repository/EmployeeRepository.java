package com.cmpe275.Lab2.repository;
import com.cmpe275.Lab2.models.CompositeKey;
import com.cmpe275.Lab2.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, CompositeKey> {

     Optional<Employee> findById(CompositeKey id);
}
