package com.example.spring6RestMvc.service.serviceImplementaitons;

import com.example.spring6RestMvc.entities.Customer;
import com.example.spring6RestMvc.exception.NotFoundException;
import com.example.spring6RestMvc.mappers.CustomerMapper;
import com.example.spring6RestMvc.model.CustomerDTO;
import com.example.spring6RestMvc.repositories.CustomerRepository;
import com.example.spring6RestMvc.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Primary
@AllArgsConstructor
@Profile("inDatabase")
public class CustomerServiceJpa implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;


    @Override
    public List<CustomerDTO> listAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::customerToCustomerDTO).toList();
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMapper.customerToCustomerDTO(customerRepository.findById(id).orElse(null)));
    }

    @Override
    public CustomerDTO save(CustomerDTO customerData) {
        Customer customer = customerRepository.save(customerMapper.customerDTOToCustomer(customerData));
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public Optional<CustomerDTO> put(UUID customerId, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> returnValue = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(
                foundCustomer -> {
                    foundCustomer.setCustomerName(customer.getCustomerName());
                    returnValue.set(Optional.of(customerMapper.customerToCustomerDTO(customerRepository.save(foundCustomer))));
                },
                () -> { returnValue.set(Optional.empty()); }
        );

        return returnValue.get();


    }

    @Override
    public Boolean delete(UUID uuid) {
        Boolean found = ( customerRepository.findById(uuid).isPresent());
        if ( found ) customerRepository.deleteById(uuid);
        return found;
    }

    @Override
    public Optional<CustomerDTO> patchById(UUID customerId, CustomerDTO customerData) {
        AtomicReference<Optional<CustomerDTO>> customerReference = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(
                foundCustomer -> {
                    if ( foundCustomer.getCustomerName().compareTo(customerData.getCustomerName()) == 0)
                        customerData.setCustomerName(foundCustomer.getCustomerName());
                    customerReference.set(Optional.of(customerData));
                },
                () -> { customerReference.set(Optional.empty()); });

        return customerReference.get();
    }
}