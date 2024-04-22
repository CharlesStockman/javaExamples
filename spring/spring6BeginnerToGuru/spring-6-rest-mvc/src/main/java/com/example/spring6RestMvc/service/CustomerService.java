package com.example.spring6RestMvc.service;

import com.example.spring6RestMvc.model.Customer;

import java.util.ArrayList;
import java.util.UUID;

public interface CustomerService {
    ArrayList<Customer> listAllCustomers();

    Customer getCustomerById(UUID id);

    Customer save(Customer customerData);

    void put(UUID customerId, Customer customer);

    Customer delete(UUID uuid);

    void patchById(UUID customerId, Customer customerData);
}
