package com.cmpe275.Lab2.controller;

import com.cmpe275.Lab2.mapper.EmployerMapper;
import com.cmpe275.Lab2.model.response.EmployerDto;
import com.cmpe275.Lab2.models.Employer;
import com.cmpe275.Lab2.repository.EmployerRepository;
import com.cmpe275.Lab2.service.EmployerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployerController {

    @Autowired
    private EmployerService employerService;
    private final EmployerRepository employerRepository;
    private final EmployerMapper employerMapper;

    public EmployerController(EmployerService employerService, EmployerRepository employerRepository, EmployerMapper employerMapper) {
        this.employerService = employerService;
        this.employerRepository = employerRepository;
        this.employerMapper = employerMapper;
    }

    @PostMapping(value = "/addEmployer")
    public void addEmployer(@RequestBody Employer employer){
        employerService.addEmployer(employer);
    }

    @GetMapping(value = "employer/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public EmployerDto getEmployer(@PathVariable @NotNull Long id) {
        return employerMapper.map(employerService.findEmployer(id));
    }

}
