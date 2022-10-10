package customer.controller;

import customer.dto.Customer;
import customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Accepts https verbs for the Customer Rest Client
 */
@Slf4j
@RestController
@RequestMapping("api/v1/customer")
public class Controller {
    private final CustomerService customerService;

    public Controller(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Gets the customer by ID
     * @param customerId        The unique id of a single customer
     * @return                  An instance of customer
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable UUID customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        ResponseEntity<Customer> customerResponse = new ResponseEntity<>(customer, HttpStatus.OK);
        log.debug(String.format("Charles Stockman in the controller for get the uuid:%s we received %s", customerId,customer.toString()));

        return customerResponse;
    }

    /**
     * Create a customer
     *
     * @param customer      The instance with the data to create the customer
     * @return              A key/value pair in the header with the id of the customer in the HttpHeader
     */
    @PostMapping()
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        UUID uuid = customerService.createCustomer(customer.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/customer/" + uuid.toString());
        log.debug(String.format("Charles Stockman in the controller for create  %s", customer.toString()));

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    /**
     * Updates a single customer
     *
     * @param customerId        The unique id of a single customer
     * @param customer          The information needed to update the customer
     *
     * @return                  A status code of No Content
     */
    @PutMapping("/{customerId}")
    public ResponseEntity<?> updatesCustomer(@PathVariable UUID customerId, @RequestBody Customer customer) {
        customerService.update(customerId, customer);
        log.debug(String.format("Charles Stockman in the controller for update  %s", customer.toString()));

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Delete a customer from the data store
     *
     * @param customerId        The unique id which identifies one customer
     *
     * @return The Http Status for No Content
     */
    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequest(@PathVariable UUID customerId) {
        customerService.delete((customerId));
        log.debug(String.format("Charles Stockman in the controller for delete  %s", customerId));
    }




}
