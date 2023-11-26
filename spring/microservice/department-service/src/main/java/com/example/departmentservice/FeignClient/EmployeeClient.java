package com.example.departmentservice.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="DepartmentService", path = "/employee-app/api", configuration = DepartmentAppConfig.class)
public interface EmployeeClient {
}