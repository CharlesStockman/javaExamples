package com.example.spring6RestMvc.repositories;

import com.example.spring6RestMvc.entities.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSaveCustomer() {
        Customer customer = customerRepository.save( Customer.builder()
                .customerName("Chuck")
                .build());

        customerRepository.flush();

        Assertions.assertNotNull(customer);
        Assertions.assertNotNull(customer.getId());
    }
}
