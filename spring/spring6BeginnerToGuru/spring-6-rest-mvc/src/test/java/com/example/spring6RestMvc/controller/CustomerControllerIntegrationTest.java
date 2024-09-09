package com.example.spring6RestMvc.controller;

import com.example.spring6RestMvc.entities.Beer;
import com.example.spring6RestMvc.entities.Customer;
import com.example.spring6RestMvc.exception.NotFoundException;
import com.example.spring6RestMvc.mappers.CustomerMapper;
import com.example.spring6RestMvc.model.BeerDTO;
import com.example.spring6RestMvc.model.BeerStyle;
import com.example.spring6RestMvc.model.CustomerDTO;
import com.example.spring6RestMvc.repositories.CustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
class CustomerControllerIntegrationTest {

    @Autowired
    private CustomerController customerController;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @PostConstruct
    public void showEnvironment() throws NoSuchFieldException, IllegalAccessException {

        // Show the current implementation the BeerService is using.
        Field serviceField = customerController.getClass().getDeclaredField("customerService");
        serviceField.setAccessible(true);
        log.error("Charles Stockman: The Service class being used is  " + serviceField.get(customerController).getClass().getCanonicalName());
    }

    @Test
    public void getCustomersTest() {
        List<CustomerDTO> customers = customerController.listAllCustomers();
        Assertions.assertThat(customers.size()).isEqualTo(2);
    }

    @Transactional
    @Rollback
    @Test
    void getCustomersDatabaseEmpty() {
        customerRepository.deleteAll();
        List<CustomerDTO> dtos = customerController.listAllCustomers();
        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    public void getByIdTest() throws ClassNotFoundException {
        Customer customer = customerRepository.findAll().getFirst();
        CustomerDTO customerDTO = customerController.getCustomerById(customer.getId());
        Assertions.assertThat(customerDTO.getCustomerName()).isEqualTo(customer.getCustomerName());
    }

    @Test
    @Transactional
    public void getByIdExceptionTest() {
        assertThrows(NotFoundException.class, () -> customerController.getCustomerById(UUID.randomUUID()));
    }

    @Test
    @Transactional
    @Rollback
    public void saveCustomer() throws ClassNotFoundException {
        CustomerDTO customerDTO = CustomerDTO.builder().customerName("New Beer -- chuck").build();
        ResponseEntity<CustomerDTO> responseEntity = customerController.createCustomer(customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID saveUUID = UUID.fromString(locationUUID[4]);

        Optional<Customer> customer = customerRepository.findById(saveUUID);
        assert(customer.isPresent());
    }

    @Test
    @Rollback
    @Transactional
    void updateExistingBeer() {
        Customer customer = customerRepository.findAll().getFirst();
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setId(null);
        customerDTO.setVersion(null);

        final String beerName = "UPDATED";
        customerDTO.setCustomerName(beerName);

        ResponseEntity<CustomerDTO> responseEntity = customerController.putCustomer(customer.getId(), customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        // The beer is stale since we made changes in memory, but not the database.  The fix is to get the original
        // beer from the database
        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo(beerName);
    }

    @Test
    void testDeleteByIDNotFound() {
        assertThrows(
                NotFoundException.class,
                () -> customerController.putCustomer(UUID.randomUUID(), CustomerDTO.builder().build()));
    }

    @Test
    @Transactional
    @Rollback
    void deleteCustomerByIdFound() {
        Customer customer = customerRepository.findAll().getFirst();

        ResponseEntity customerDTOResponseEntity = customerController.deleteCustomer(customer.getId());
        assertThat(customerDTOResponseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        assertThat(customerRepository.findById(customer.getId()).isPresent()).isFalse();
    }


    @Test
    public void testPatchWithInvalidId() {
        Customer customer = customerRepository.findAll().getFirst();
        assertThrows( NotFoundException.class, () -> customerController.patchCustomer(UUID.randomUUID(), customerMapper.customerToCustomerDTO(customer)));
    }

    @Test
    @Transactional
    @Rollback
    public void testPatchWithValidId() throws ClassNotFoundException {

        // Expected Beer
        CustomerDTO expectedCustomer = customerController.listAllCustomers().getFirst();
        expectedCustomer.setCustomerName("Charles Stockman");

        customerController.patchCustomer(expectedCustomer.getId(), expectedCustomer);
        CustomerDTO actualBeer = customerController.getCustomerById(expectedCustomer.getId());
        assertThat(actualBeer).isEqualTo(expectedCustomer);

    }

    @Test
    @Transactional
    @Rollback
    public void testPatchWithNValidIdButNoChanges() throws ClassNotFoundException {

        // Expected Beer
        CustomerDTO expectedBeer = customerController.listAllCustomers().getFirst();

        log.debug("Expected Beer = " + expectedBeer.toString());

        customerController.patchCustomer(expectedBeer.getId(), expectedBeer);
        CustomerDTO actualBeer = customerController.getCustomerById(expectedBeer.getId());
        assertThat(actualBeer).isEqualTo(expectedBeer);

    }





}