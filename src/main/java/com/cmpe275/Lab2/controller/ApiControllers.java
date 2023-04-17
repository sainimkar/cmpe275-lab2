package com.cmpe275.Lab2.controller;

import com.cmpe275.Lab2.models.Employee;
import com.cmpe275.Lab2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiControllers {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping(value="/")
    public String getPage(){
        return "Hello World";
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public void addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }
}
