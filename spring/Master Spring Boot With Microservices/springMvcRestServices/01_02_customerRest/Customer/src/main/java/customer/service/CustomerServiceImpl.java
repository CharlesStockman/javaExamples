package customer.service;

import customer.dao.CustomerDao;
import customer.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Business Logic for the Customer Rest API
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public UUID createCustomer(String name) {
        Customer customer =  customerDao.create(name);
        log.debug("Charles Stockman service create name = " + name);
        return customer.getPrimaryKey();
    }

    @Override
    public Customer getCustomerById(UUID uuid) {
        Customer customer = Customer.builder().primaryKey(UUID.randomUUID()).name("Charles Stockman").build();
        log.debug("Charles Stockman service get uuid = " + uuid );
        return customer;
    }

    @Override
    public void update(UUID uuid, Customer customer) {
        customerDao.update(uuid, customer);
        log.debug("Charles Stockman service update Customer = " + customer.toString() );
    }

    @Override
    public void delete(UUID uuid) {
        customerDao.delete(uuid);
        log.debug("Charles Stockman service delete uuid = " + uuid);
    }
}
