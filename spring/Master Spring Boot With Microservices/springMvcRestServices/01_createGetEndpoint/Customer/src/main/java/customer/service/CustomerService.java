package customer.service;

import customer.dto.Customer;

import java.util.UUID;

/**
 * Contains the signatures of the functions that the controll can access
 */
public interface CustomerService {
    /**
     * @param uuid      The Universal Identifier which is the primary key of the customner
     * @return          A customer instance
     */
    public Customer getCustomerById(UUID uuid);
}
