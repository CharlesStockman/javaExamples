package com.example.spring6RestMvc.service.serviceImplementaitons;

import com.example.spring6RestMvc.mappers.CustomerMapper;
import com.example.spring6RestMvc.model.CustomerDTO;
import com.example.spring6RestMvc.repositories.CustomerRepository;
import com.example.spring6RestMvc.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@AllArgsConstructor
public class CustomerServiceJpa implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;


    @Override
    public List<CustomerDTO> listAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::customerToCustomerDTO).toList();
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMapper.customerToCustomerDTO(customerRepository.findById(id).orElse(null)));
    }

    @Override
    public CustomerDTO save(CustomerDTO customerData) {
        return null;
    }

    @Override
    public void put(UUID customerId, CustomerDTO customer) {

    }

    @Override
    public CustomerDTO delete(UUID uuid) {
        return null;
    }

    @Override
    public void patchById(UUID customerId, CustomerDTO customerData) {

    }
}
