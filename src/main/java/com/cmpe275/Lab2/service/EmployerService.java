package com.cmpe275.Lab2.service;


import com.cmpe275.Lab2.models.Employer;
import com.cmpe275.Lab2.repository.EmployerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    private EmployerService employerService;
    @Transactional
    public void addEmployer(Employer employer){
        employerRepository.save(employer);
    }

    public Employer findEmployer(final Long id) {
        return employerRepository.findById(id).orElseThrow(() -> new RuntimeException("sdfsd"));
    }
}
