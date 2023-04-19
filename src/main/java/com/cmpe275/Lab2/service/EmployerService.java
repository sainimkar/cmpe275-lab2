package com.cmpe275.Lab2.service;


import com.cmpe275.Lab2.exception.OperationNotAllowedException;
import com.cmpe275.Lab2.models.Employee;
import com.cmpe275.Lab2.models.Employer;
import com.cmpe275.Lab2.repository.EmployeeRepository;
import com.cmpe275.Lab2.repository.EmployerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;
    private EmployeeRepository employeeRepository;


    private EmployerService employerService;

    //Create Employer
    @Transactional
    public Employer addEmployer(Employer employer){
        if (employer.getName() == null) {
            throw new RuntimeException("Employer name is required");
        }
        return employerRepository.save(employer);
    }

    //Get Employer
    public Employer findEmployer(final Long id) {
        return employerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employer not found"));
    }

    //Update Employer
    @Transactional
    public Employer updateEmployer(final Long id, final Employer fromRequest) {
        final Employer existingEmployer = findEmployer(id);
        existingEmployer.update(fromRequest);
        return existingEmployer;
    }

    //Delete Employer //TODO
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
