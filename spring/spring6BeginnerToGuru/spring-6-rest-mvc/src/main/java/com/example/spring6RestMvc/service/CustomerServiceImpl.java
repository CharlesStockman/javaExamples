package com.example.spring6RestMvc.service;

import com.example.spring6RestMvc.model.Customer;
import com.example.spring6RestMvc.util.MetaDataFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, Customer> customers;

    public CustomerServiceImpl() {
        Customer customer1 = Customer.builder()
                .customerName("Charles Stockman")
                .metaData(MetaDataFactory.createMetaData())
                .build();

        Customer customer2 = Customer.builder()
                .customerName("Charlie Stockman")
                .metaData(MetaDataFactory.createMetaData())
                .build();

        customers = new HashMap<>();
        customers.put(customer1.getMetaData().getId(), customer1);
        customers.put(customer2.getMetaData().getId(), customer2);
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
                .customerName(customerData.getCustomerName())
                .metaData(MetaDataFactory.createMetaData())
                .build();

        customers.put(customer.getMetaData().getId(), customer);
        return customer;
    }

    @Override
    public void put(UUID customerId, Customer customerData) {
        Customer customer = customers.get(customerId);
        MetaDataFactory.updateMetaData(customerData.getMetaData());
    }

    @Override
    public Customer delete(UUID uuid) {
        return customers.remove(uuid);
    }

    @Override
    public void patchById(UUID customerId, Customer customerData) {
        Customer customer = customers.get(customerId);
        if (StringUtils.hasText(customerData.getCustomerName())) customer.setCustomerName(customerData.getCustomerName());
        MetaDataFactory.updateMetaData(customer.getMetaData());
    }
}
