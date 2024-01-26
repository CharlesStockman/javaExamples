package com.seleniumexpress.addressapp.addressservice.controller;

import com.seleniumexpress.addressapp.addressservice.response.AddressResponse;
import com.seleniumexpress.addressapp.addressservice.response.EmployeeToAddressMapResponse;
import com.seleniumexpress.addressapp.addressservice.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AddressController {

    private AddressService addressService;


    public AddressController(AddressService service) {
        this.addressService = service;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        ResponseEntity<String> test = ResponseEntity.ok("Helloworld");
        return test;
    }

    @GetMapping("/address/{employeeId}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int id ) {
        AddressResponse addressResponse =  addressService.findAddressByEmployeeId(id);
        ResponseEntity<AddressResponse> response = ResponseEntity.status(HttpStatus.OK).body(addressResponse);
        return response;
    }

    @GetMapping("/address/")
    public ResponseEntity<EmployeeToAddressMapResponse> getAllAddress() {
        EmployeeToAddressMapResponse data = addressService.getAllAddresses();
        ResponseEntity<EmployeeToAddressMapResponse> response = ResponseEntity.status(HttpStatus.OK).body(data);
        return response;
    }

}