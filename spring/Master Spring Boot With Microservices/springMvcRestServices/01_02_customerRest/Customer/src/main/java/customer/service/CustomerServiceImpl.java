package customer.service;

import customer.dto.Customer;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Business Logic for the Customer Rest API
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer getCustomerById(UUID uuid) {
        Customer customer = Customer.builder().primaryKey(UUID.randomUUID()).name("Charles Stockman").build();
        return customer;
    }
}
