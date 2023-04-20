package com.cmpe275.Lab2.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.cmpe275.Lab2.mapper.EmployeeMapper;
import com.cmpe275.Lab2.model.response.EmployeeDto;
import com.cmpe275.Lab2.models.Address;
import com.cmpe275.Lab2.models.Employee;
import com.cmpe275.Lab2.models.Employer;
import com.cmpe275.Lab2.service.EmployeeService;
import com.cmpe275.Lab2.service.EmployerService;
import com.cmpe275.Lab2.utility.ValidatorUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.RuntimeErrorException;
import java.util.*;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

//    @GetMapping(value="/")
//    public String getPage(){
//        return "Hello World";
//    }

    @RequestMapping(value = "/employer/{employerId}/employee", method = RequestMethod.POST)
    public EmployeeDto createEmployee(@PathVariable @NotNull String employerId, @RequestParam Map<String, String> params) {
        ValidatorUtil.validateParams(params, Arrays.asList("name", "email"));
        ValidatorUtil.validateRestrictedParam(params, Arrays.asList("collaborators", "reports"));

        params.put("employerId", employerId);
        final Employee createdEmployee = employeeService.addEmployee(
                employeeMapper.map(params),
                params.get("managerId"),
                employerId
        );

        return employeeMapper.map(createdEmployee);
    }

    @GetMapping(value = "employer/{employerId}/employee/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getEmployee(@PathVariable @NotNull String employerId , @PathVariable @NotNull long id) {
        Optional<Employee> emp = employeeService.findEmployee(id, employerId);
        if(!emp.isEmpty()) {
            return new ResponseEntity<EmployeeDto>(employeeMapper.map(emp.get()), HttpStatus.OK);
        }
        return new ResponseEntity<String>("EMPLOYEE NOT FOUND ", HttpStatus.BAD_REQUEST);
    }


    @PutMapping(value = "/employer/{employerId}/employee/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto updateEmployee(@PathVariable @NotNull String employerId, @PathVariable @NotNull long id, @RequestParam Map<String, String> params) {
        ValidatorUtil.validateParams(params, Arrays.asList("email"));
        ValidatorUtil.validateRestrictedParam(params, Arrays.asList("collaborators", "reports"));

        final Employee updatedEmployee = employeeService.updateEmployee(
                id,
                employeeMapper.map(params),
                params.get("managerId"),
                employerId
        );

        return employeeMapper.map(updatedEmployee);

    }

    @DeleteMapping(value = "/{id}/{employerId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto deleteEmployee(@PathVariable @NotNull long id, @PathVariable @NotNull String employerId) {
        final Employee deletedEmployee = employeeService.deleteEmployee(id, employerId);
        return employeeMapper.map(deletedEmployee);
    }
}
