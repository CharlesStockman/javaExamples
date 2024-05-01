package com.example.spring6RestMvc.controller;

import com.example.spring6RestMvc.exception.NotFoundException;
import com.example.spring6RestMvc.model.CustomerDTO;
import com.example.spring6RestMvc.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> listAllCustomers() {
        return customerService.listAllCustomers();
    }

    @RequestMapping("{customerId}")
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID id) throws ClassNotFoundException {
        return customerService.getCustomerById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerData) {
        CustomerDTO customer = customerService.save(customerData);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "/api/vi/customer/" + customer.getMetaData().getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<CustomerDTO> putCustomer(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customer) {
        customerService.put(customerId, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable("customerId") UUID uuid ) {
        customerService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{customerId}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customerData ) {
        customerService.patchById(customerId, customerData);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
