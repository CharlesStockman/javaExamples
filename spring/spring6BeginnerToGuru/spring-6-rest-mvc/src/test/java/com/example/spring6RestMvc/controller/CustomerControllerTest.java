package com.example.spring6RestMvc.controller;


import com.example.spring6RestMvc.model.Customer;
import com.example.spring6RestMvc.service.CustomerService;
import com.example.spring6RestMvc.service.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<Customer> customerArgumentCaptor;

    CustomerService customerServiceImpl;

    @BeforeEach
    public void setup() {
        customerServiceImpl = new CustomerServiceImpl();
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer customer = customerServiceImpl.listAllCustomers().getFirst();

        given(customerService.getCustomerById(customer.getMetaData().getId())).willReturn(customer);

        mockMvc.perform(get("/api/v1/customer/" + customer.getMetaData().getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.metaData.id", is(customer.getMetaData().getId().toString())))
                .andExpect(jsonPath("$.customerName", is(customer.getCustomerName())));
    }

    @Test
    public void testListAllCustomers() throws Exception {
        given(customerService.listAllCustomers()).willReturn(customerServiceImpl.listAllCustomers());

        mockMvc.perform(get("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)));

    }

    @Test
    public void testCreateCustomer() throws Exception {
        Customer customer = customerServiceImpl.listAllCustomers().getFirst();

        given(customerService.save(any(Customer.class))).willReturn(customerServiceImpl.listAllCustomers().get(1));

        // When setting the content must set the content type too or an HTTP Error Code 415 is returned.
        mockMvc.perform(post("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Customer customer = customerServiceImpl.listAllCustomers().getFirst();

        mockMvc.perform(put("/api/v1/customer/" + customer.getMetaData().getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isNoContent());

        verify(customerService).put(any(UUID.class), any(Customer.class));

    }

    @Test
    public void testDelete() throws Exception {
        Customer customer = customerServiceImpl.listAllCustomers().getFirst();

        mockMvc.perform(delete("/api/v1/customer/" + customer.getMetaData().getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(customerService).delete(uuidArgumentCaptor.capture());
        assertThat(customer.getMetaData().getId()).isEqualTo(uuidArgumentCaptor.getValue());

    }

    @Test
    public void testPatch() throws Exception {
        Customer customer = customerServiceImpl.listAllCustomers().getFirst();

        Map<String, String> customerMap = new HashMap<>();
        customerMap.put("customerName", "New Name");

        mockMvc.perform( patch( "/api/v1/customer/" + customer.getMetaData().getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerMap)))
                .andExpect(status().isNoContent());

        verify(customerService).patchById(uuidArgumentCaptor.capture(), customerArgumentCaptor.capture());
        assertThat(customer.getMetaData().getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(customerMap.get("customerName")).isEqualTo(customerArgumentCaptor.getValue().getCustomerName());
    }
}


