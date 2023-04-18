package com.cmpe275.Lab2.service;


import com.cmpe275.Lab2.exception.OperationNotAllowedException;
import com.cmpe275.Lab2.models.Employee;
import com.cmpe275.Lab2.models.Employer;
import com.cmpe275.Lab2.repository.EmployeeRepository;
import com.cmpe275.Lab2.repository.EmployerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;
    private EmployeeRepository employeeRepository;

    private EmployerService employerService;
    @Transactional
    public Employer addEmployer(Employer employer){
        return employerRepository.save(employer);
    }

    @Transactional
    public Employer createEmployer(final Employer employer) {
        return employerRepository.save(employer);
    }

    public Employer findEmployer(final Long id) {
        return employerRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    @Transactional
    public Employer updateEmployer(final Long id, final Employer fromRequest) {
        final Employer existingEmployer = findEmployer(id);
        existingEmployer.update(fromRequest);
        return existingEmployer;
    }

    @Transactional
    public Employer deleteEmployer(final Long id) {
        final Employer toDelete = findEmployer(id);
//        final List<Employee> allEmployee = employeeRepository.findAll();
//        for (Employee emp: allEmployee) {
//            if (emp.getEmployer().getId() == id) {
//                throw new OperationNotAllowedException(
//                        "There are still employee belonging to this employer",
//                        id.toString()
//                );
//            }
//        }
        employerRepository.delete(toDelete);
        return toDelete;
    }
}
