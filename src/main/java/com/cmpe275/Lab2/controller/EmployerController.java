package com.cmpe275.Lab2.controller;

import com.cmpe275.Lab2.exception.ConstraintViolationException;
import com.cmpe275.Lab2.mapper.EmployerMapper;
import com.cmpe275.Lab2.model.response.EmployerDto;
import com.cmpe275.Lab2.models.Employer;
import com.cmpe275.Lab2.repository.EmployerRepository;
import com.cmpe275.Lab2.service.EmployerService;
import com.cmpe275.Lab2.utility.ValidatorUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Map;

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


    //create
    @PostMapping(value = "/employer")
    @ResponseBody
    public EmployerDto addEmployer(@RequestParam Map<String, String> params) {
        try {
            ValidatorUtil.validateParams(params, Arrays.asList("name"));
        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

        final Employer employer = employerService.addEmployer(employerMapper.map(params));

        return employerMapper.map(employer);
    }


    @GetMapping(value = "employer/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public EmployerDto getEmployer(@PathVariable @NotNull Long id) {
        return employerMapper.map(employerService.findEmployer(id));
    }

    @PutMapping(value = "employer/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public EmployerDto updateEmployer(@PathVariable @NotNull Long id, @RequestParam Map<String, String> params) {
        ValidatorUtil.validateParams(params, Arrays.asList("name"));
        final Employer updatedEmployer = employerService.updateEmployer(
                id,
                employerMapper.map(params)
        );

        return employerMapper.map(updatedEmployer);

    }

    @DeleteMapping(value = "employer/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public EmployerDto deleteEmployer(@PathVariable @NotNull long id) {
        final Employer deletedEmployer = employerService.deleteEmployer(id);
        return employerMapper.map(deletedEmployer);
    }
}
