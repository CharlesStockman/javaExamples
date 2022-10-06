package customer.controller;

import customer.dto.Customer;
import customer.service.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Accepts https verbs for the Customer Rest Client
 */
@RestController
@RequestMapping("api/v1/customer")
public class Controller {
    private final CustomerServiceImpl customerService;

    public Controller(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String test() {
        return "Hello World";
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable UUID customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        ResponseEntity<Customer> customerResponse = new ResponseEntity<>(customer, HttpStatus.OK);
        return customerResponse;
    }
}
