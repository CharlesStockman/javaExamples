package com.seleniumexpress.employeeapp.employeeservice.controller;

import com.seleniumexpress.employeeapp.employeeservice.response.EmployeeResponse;
import com.seleniumexpress.employeeapp.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
        List<EmployeeResponse> employeeResponses = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponses);
    }

    @GetMapping("/showInstances")
    public ResponseEntity<String> getInstances() {
        String instanceData = employeeService.showInstances();
        return ResponseEntity.status(HttpStatus.OK).body(instanceData);
    }

    @GetMapping("/showRandomInstance")
    public ResponseEntity<String> getInstance() {
        String instanceData = employeeService.getRandomService();
        return ResponseEntity.ok(instanceData);
    }
}
