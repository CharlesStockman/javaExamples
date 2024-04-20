package com.example.spring6RestMvc.service;

import com.example.spring6RestMvc.model.Customer;

import java.util.ArrayList;
import java.util.UUID;

public interface CustomerService {
    ArrayList<Customer> listAllCustomers();

    Customer getCustomerById(UUID id);
}
