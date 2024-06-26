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

    Optional<CustomerDTO> put(UUID customerId, CustomerDTO customer);

    Boolean delete(UUID uuid);

    Optional<CustomerDTO> patchById(UUID customerId, CustomerDTO customerData);
}
