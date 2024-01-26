package com.seleniumexpress.employeeapp.employeeservice.response;

import lombok.Data;

@Data
public class AddressResponse {

    private int id;
    private String lane1;
    private String lane2;
    private long zip;
    private String state;
}
