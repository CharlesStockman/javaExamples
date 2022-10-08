package customer.service;

import customer.dto.Customer;

import java.util.UUID;

/**
 * Contains the signatures of the functions that the controll can access
 */
public interface CustomerService {

    /**
     * Create a Customer
     *
     * @param name      The name of the customer stored in the database
     * @return          the primary key to identify the specific customer
     */
     UUID createCustomer(String name);

    /**
     * @param uuid      The Universal Identifier which is the primary key of the customner
     * @return          A customer instance
     */
     Customer getCustomerById(UUID uuid);

    /**
     * Updates the Customer
     *
     * @param uuid       The primary key to identify the customer
     * @param customer   The instnace that holds all the new information about the customer
     */
    void update(UUID uuid, Customer customer);

    /**
     * Deletes the Customer instance
     *
     * @param uuid   The primary key used to delete the customer
     */
    void delete(UUID uuid);


}
