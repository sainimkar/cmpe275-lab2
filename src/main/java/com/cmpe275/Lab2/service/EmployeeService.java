package com.cmpe275.Lab2.service;

import com.cmpe275.Lab2.exception.OperationNotAllowedException;
import com.cmpe275.Lab2.models.CompositeKey;
import com.cmpe275.Lab2.models.Employee;
import com.cmpe275.Lab2.models.Employer;
import com.cmpe275.Lab2.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    EmployerService employerService;

    public Employee addEmployee(
            final Employee toCreate,
            final String managerId,
            final String employerId
    ) {
        Employee newEmployee = setManagerAndEmployer(toCreate, managerId, employerId);
        System.out.println(newEmployee.toString(newEmployee));
        return employeeRepository.save(newEmployee);
    }

    public Optional<Employee> findEmployee(long employeeId, String employerId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(new CompositeKey(employeeId, employerId));
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            System.out.println(employee.toString(employee));
        }
        return employeeRepository.findById(new CompositeKey(employeeId, employerId));
    }


    @Transactional
    public Employee updateEmployee(
            final Long id,
            final Employee fromRequest,
            final String managerId,
            final String employerId
    ) {
        Employee existingEmployee = (Employee)findEmployee(id, employerId).orElse(null);
        existingEmployee = setManagerAndEmployer(existingEmployee, managerId, employerId);
        existingEmployee.update(fromRequest);
        return existingEmployee;
    }

    @Transactional
    public Employee deleteEmployee(final Long id, final String employerId) {
        final Employee toDelete = (Employee)findEmployee(id, employerId).orElse(null);
        if (toDelete.getReports().size() > 0) {
            throw new OperationNotAllowedException("Employee still has reports", id.toString());
        }
        Employee beforeDelete = new Employee(toDelete);
        toDelete.removeCollaborators();
        employeeRepository.delete(toDelete);
        return beforeDelete;
    }

    @Transactional
    public void createCollaboration(final long id1, final String employerId1, final long id2, final String employerId2) {

        final Employee e1 = (Employee) findEmployee(id1, employerId1).orElse(null);
        final Employee e2 = (Employee) findEmployee(id2, employerId2).orElse(null);

        if (e1.getCollaborators() == null) {
            e1.setCollaborators(new ArrayList<Employee>());
            e1.getCollaborators().add(e2);
            System.out.println("step 1 done" + e1.toString(e1));
        } else {
            e1.addCollaborator(e2);
        }

        if (e2.getCollaborators() == null) {
            e2.setCollaborators(new ArrayList<Employee>());
            e2.getCollaborators().add(e1);
            System.out.println("step 2 done" + e2.toString(e2));
        } else {
            e2.addCollaborator(e1);
        }
        //e1.addCollaborator(e2);
        //e2.addCollaborator(e1);
        employeeRepository.save(e2);
        employeeRepository.save(e1);
    }


    @Transactional
    public void deleteCollaboration(final long id1, final String employerId1, final long id2, final String employerId2) {

        final Employee e1 = (Employee) findEmployee(id1, employerId1).orElse(null);
        final Employee e2 = (Employee) findEmployee(id2, employerId2).orElse(null);

        e1.removeCollaborator(e2);
        e2.removeCollaborator(e1);
    }

    private Employee setManagerAndEmployer(Employee employee, String managerId, String employerId) {
        // TODO Circular manager loop
        if (Objects.nonNull(employee.getEmployer()) &&
                !Long.valueOf(employerId).equals(employee.getEmployer().getId())) {
            // Employee is changing employer
            if (Objects.nonNull(employee.getManager())) {
                for (Employee report : employee.getReports()) {
                    report.setManager(employee.getManager());
                }
            } else {
                for (Employee report : employee.getReports()) {
                    report.setManager(null);
                }
            }
            employee.getReports().clear();
        }
        // May be changing manager under same employer  New employee creation OR
        if (Objects.nonNull(managerId)) {
            Optional<Employee> manager = findEmployee(Long.valueOf(managerId), employee.getEmployerId());

            if(!manager.isEmpty()) {
                if (Long.valueOf(employerId).equals(Long.valueOf(manager.get().getEmployerId()))) {
                    employee.setManager(manager.get());
                } else {
                    throw new OperationNotAllowedException("Manager should be from same Employer", employerId);
                }
            } else {
                employee.setManager(employee);
            }
        }

        final Employer employer = employerService.findEmployer(Long.valueOf(employerId));
        employee.setEmployer(employer);
        return employee;
    }



}
