package com.emp.persp.controller;

import com.emp.persp.dao.EmployeeService;
import com.emp.persp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/retrieve")
    public Employee retrieveEmployee(@RequestParam("id") Long id) {

        return employeeService.getEmpById(id);
    }
}
