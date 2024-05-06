package com.example.spring6RestMvc.service.serviceImplementaitons;

import com.example.spring6RestMvc.model.CustomerDTO;
import com.example.spring6RestMvc.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, CustomerDTO> customers;

    public CustomerServiceImpl() {
        CustomerDTO customer1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Charles Stockman")
                .version(1)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Charlie Stockman")
                .version(1)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        customers = new HashMap<>();
        customers.put(customer1.getId(), customer1);
        customers.put(customer2.getId(), customer2);
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
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        customers.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public void put(UUID customerId, CustomerDTO customerData) {
        CustomerDTO customer = customers.get(customerId);
    }

    @Override
    public CustomerDTO delete(UUID uuid) {
        return customers.remove(uuid);
    }

    @Override
    public void patchById(UUID customerId, CustomerDTO customerData) {
        CustomerDTO customer = customers.get(customerId);
        if (StringUtils.hasText(customerData.getCustomerName())) customer.setCustomerName(customerData.getCustomerName());
    }
}
