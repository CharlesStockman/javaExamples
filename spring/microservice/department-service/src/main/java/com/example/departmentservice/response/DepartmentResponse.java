package com.example.departmentservice.response;

import lombok.Data;

@Data
public class DepartmentResponse {

    private Long id;
    private String departmentName;
    private EmployeeResponse employee;
}
