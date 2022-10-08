package customer.dao;

import customer.dto.Customer;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface CustomerDao {

    /**
     * Create a Customer entry in the database
     *
     * @param name      The name of the customer stored in the database
     * @return          the primary key to identify the specific customer
     */
     Customer create(String name);

    /**
     * Retrieves the Customer from the database
     *
     * @param uuid      The primary key used to find the customer
     * @return          The customer that has the primary key
     */
     Customer read(UUID uuid );

    /**
     * Updates the Customer entry in the database
     *
     * @param uuid       The primary key to identify the customer
     * @param customer   The new information which will update the customer with primary key uuid
     */
     void update(UUID uuid, Customer customer);

    /**
     * Deletes the customer from the database
     *
     * @param uuid          The primary key to identify the customer
     */
     void  delete(UUID uuid);
}