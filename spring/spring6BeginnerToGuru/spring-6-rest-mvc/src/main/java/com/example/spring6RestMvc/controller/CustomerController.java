package com.example.spring6RestMvc.controller;

import com.example.spring6RestMvc.model.Customer;
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
    public List<Customer> listAllCustomers() {
        return customerService.listAllCustomers();
    }

    @RequestMapping("{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") UUID id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customerData) {
        Customer customer = customerService.save(customerData);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "/api/vi/customer/" + customer.getMetaData().getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<Customer> putCustomer(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {
        customerService.put(customerId, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") UUID uuid ) {
        customerService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{customerId}")
    public ResponseEntity<Customer> patchCustomer(@PathVariable("customerId") UUID customerId, @RequestBody Customer customerData ) {
        customerService.patchById(customerId, customerData);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
