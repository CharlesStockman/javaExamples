package com.student.microservice.controller;

import com.student.microservice.service.FinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import java.util.Random;

/**
 * A controller that handles fines incurred by students.
 */
@RestController
@RequestMapping("/fines")
public class FinesController {

    @Autowired
    private FinesService finesService;
    
    @GetMapping(value="/{id}")
    public Double getFine(@PathVariable  int studentId) {
        return finesService.getFine(studentId);
    }
}
