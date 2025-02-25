package com.example.springAop.service.serviceImplementaitons;

import com.example.springAop.model.EmployeeHours;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    public List<EmployeeHours> service(List<EmployeeHours> employeeHours) {
        return employeeHours;
    }
}
