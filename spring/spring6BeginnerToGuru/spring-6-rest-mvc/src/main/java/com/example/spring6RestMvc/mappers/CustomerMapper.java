package com.example.spring6RestMvc.mappers;

import com.example.spring6RestMvc.entities.Customer;
import com.example.spring6RestMvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
    CustomerDTO customerToCustomerDTO(Customer customer);
}
