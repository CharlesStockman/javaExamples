package com.example.spring6RestMvc.service;

import com.example.spring6RestMvc.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, Customer> customers;

    public CustomerServiceImpl() {
        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Charles Stockman")
                .version(1)
                .createDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Charlie Stockman")
                .version(1)
                .createDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customers = new HashMap<>();
        customers.put(customer1.getId(),customer1);
        customers.put(customer2.getId(),customer2);
    }

    @Override
    public ArrayList<Customer> listAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customers.get(id);
    }

    @Override
    public Customer save(Customer customerData) {
        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .customerName(customerData.getCustomerName())
                .version(1)
                .createDate( LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customers.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public void put(UUID customerId, Customer customerData) {
        Customer customer = customers.get(customerId);
        customer.setCustomerName(customerData.getCustomerName());
        customer.setVersion(customer.getVersion() + 1);
        customer.setLastModifiedDate(LocalDateTime.now());
    }

    @Override
    public Customer delete(UUID uuid) {
        return customers.remove(uuid);
    }

    @Override
    public void patchById(UUID customerId, Customer customerData) {
        Customer customer = customers.get(customerId);
        if (StringUtils.hasText(customerData.getCustomerName())) customer.setCustomerName(customerData.getCustomerName());
        customer.setVersion(customer.getVersion() + 1);
        customer.setLastModifiedDate(LocalDateTime.now());
    }
}
