package com.seleniumexpress.employeeapp.employeeservice.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeResponse {

    private int id;
    private String name;
    private String email;
    private String bloodGroup;
    private AddressResponse addressResponse;
}
