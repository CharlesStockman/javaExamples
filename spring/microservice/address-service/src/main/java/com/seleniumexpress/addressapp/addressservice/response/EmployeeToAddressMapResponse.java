package com.seleniumexpress.addressapp.addressservice.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class EmployeeToAddressMapResponse {

    private Map<Integer, AddressResponse> data;

}
