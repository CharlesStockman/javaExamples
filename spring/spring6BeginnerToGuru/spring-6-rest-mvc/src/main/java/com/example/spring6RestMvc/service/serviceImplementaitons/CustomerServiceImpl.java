package com.example.spring6RestMvc.service.serviceImplementaitons;

import com.example.spring6RestMvc.model.CustomerDTO;
import com.example.spring6RestMvc.service.CustomerService;
import com.example.spring6RestMvc.util.MetaDataFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, CustomerDTO> customers;

    public CustomerServiceImpl() {
        CustomerDTO customer1 = CustomerDTO.builder()
                .customerName("Charles Stockman")
                .metaData(MetaDataFactory.createMetaData())
                .build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .customerName("Charlie Stockman")
                .metaData(MetaDataFactory.createMetaData())
                .build();

        customers = new HashMap<>();
        customers.put(customer1.getMetaData().getId(), customer1);
        customers.put(customer2.getMetaData().getId(), customer2);
    }

    @Override
    public ArrayList<CustomerDTO> listAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.of(customers.get(id));
    }

    @Override
    public CustomerDTO save(CustomerDTO customerData) {
        CustomerDTO customer = CustomerDTO.builder()
                .customerName(customerData.getCustomerName())
                .metaData(MetaDataFactory.createMetaData())
                .build();

        customers.put(customer.getMetaData().getId(), customer);
        return customer;
    }

    @Override
    public void put(UUID customerId, CustomerDTO customerData) {
        CustomerDTO customer = customers.get(customerId);
        MetaDataFactory.updateMetaData(customerData.getMetaData());
    }

    @Override
    public CustomerDTO delete(UUID uuid) {
        return customers.remove(uuid);
    }

    @Override
    public void patchById(UUID customerId, CustomerDTO customerData) {
        CustomerDTO customer = customers.get(customerId);
        if (StringUtils.hasText(customerData.getCustomerName())) customer.setCustomerName(customerData.getCustomerName());
        MetaDataFactory.updateMetaData(customer.getMetaData());
    }
}
