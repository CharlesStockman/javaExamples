package customer.dao;

import customer.dto.Customer;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.UUID;

@Repository
@ToString
@Slf4j
class CustomerDaoImpl implements CustomerDao {

    private HashMap<UUID, Customer> customers = new HashMap<>();

    @Override
    public Customer create(String name) {
        Customer customer = new Customer(UUID.randomUUID(), name);
        customers.put(customer.getPrimaryKey(), customer);

        log.debug("Charles Stockman: DB Layer create: " + customers.toString());
        return customer;
    }

    @Override
    public Customer read(UUID uuid ) {
        Customer customer = customers.get(uuid);
        log.debug("Charles Stockman: read DB Layer: Retrieved for uuid " + uuid );
        return customer;
    }

    @Override
    public void update(UUID uuid, Customer newDatacustomer) {
        Customer customer = read(uuid);
        customer.setName(customer.getName());
        log.debug("Charles Stockman: update DB Layer: " + customers.toString());
    }

    @Override
    public void delete(UUID uuid) {
        customers.remove(uuid);
        log.debug("Charles Stockman delete DB Layer: " + customers.toString() );

    }
}
