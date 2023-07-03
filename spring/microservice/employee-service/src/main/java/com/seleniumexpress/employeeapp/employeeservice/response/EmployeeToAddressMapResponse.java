package com.seleniumexpress.employeeapp.employeeservice.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class EmployeeToAddressMapResponse {

    private Map<Integer, AddressResponse> data = new HashMap<>();

}
