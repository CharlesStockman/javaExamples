package com.example.springAop.controller;

import com.example.springAop.model.EmployeeHours;
import com.example.springAop.service.serviceImplementaitons.MyService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {

    MyService service;

    @GetMapping
    public void getWorkday() {
        List<EmployeeHours> employee = new ArrayList<>();
        employee = service.service(employee);

        System.out.printf(EmployeeHours.getFormatString(), "Method Name", "Name", "Arrived", "Hours", "Approved");
        for ( EmployeeHours item : employee) {
            item.displayData();
        }

    }

}