package com.example.spring6RestMvc.service;

import com.example.spring6RestMvc.model.CustomerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<CustomerDTO> listAllCustomers();

    Optional<CustomerDTO> getCustomerById(UUID id);

    CustomerDTO save(CustomerDTO customerData);

    void put(UUID customerId, CustomerDTO customer);

    CustomerDTO delete(UUID uuid);

    void patchById(UUID customerId, CustomerDTO customerData);
}
