package com.example.spring6RestMvc.controller;

import com.example.spring6RestMvc.entities.Customer;
import com.example.spring6RestMvc.exception.NotFoundException;
import com.example.spring6RestMvc.mappers.CustomerMapper;
import com.example.spring6RestMvc.model.CustomerDTO;
import com.example.spring6RestMvc.repositories.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIntegrationTest {

    @Autowired
    private CustomerController customerController;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void getCustomersTest() {
        List<CustomerDTO> customers = customerController.listAllCustomers();
        Assertions.assertThat(customers.size()).isEqualTo(2);
    }

    @Test
    public void getByIdTest() throws ClassNotFoundException {
        Customer customer = customerRepository.findAll().getFirst();
        CustomerDTO customerDTO = customerController.getCustomerById(customer.getId());
        Assertions.assertThat(customerDTO.getCustomerName()).isEqualTo(customer.getCustomerName());
    }

    @Test
    @Transactional
    public void getByIdExceptionTest() {
        assertThrows(NotFoundException.class, () -> customerController.getCustomerById(UUID.randomUUID()));
    }

    @Test
    @Transactional
    @Rollback
    public void saveCustomer() throws ClassNotFoundException {
        Customer customer = customerRepository.findAll().getFirst();
        customer.setCustomerName("test1");
        customerController.createCustomer(customerMapper.customerToCustomerDTO(customer));

        CustomerDTO actualCustomer = customerController.getCustomerById(customer.getId());
        Assertions.assertThat(actualCustomer).isEqualTo(customerMapper.customerToCustomerDTO(customer));
    }





}