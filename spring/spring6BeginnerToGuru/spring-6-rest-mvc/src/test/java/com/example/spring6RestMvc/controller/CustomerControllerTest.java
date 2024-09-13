package com.example.spring6RestMvc.controller;


import com.example.spring6RestMvc.exception.NotFoundException;
import com.example.spring6RestMvc.model.BeerDTO;
import com.example.spring6RestMvc.model.CustomerDTO;
import com.example.spring6RestMvc.service.CustomerService;
import com.example.spring6RestMvc.service.serviceImplementaitons.CustomerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    ArgumentCaptor<CustomerDTO> customerArgumentCaptor;

    CustomerService customerServiceImpl;

    @BeforeEach
    public void setup() {
        customerServiceImpl = new CustomerServiceImpl();
    }

    @Test
    public void testListAllCustomers() throws Exception {
        given(customerService.listAllCustomers()).willReturn(customerServiceImpl.listAllCustomers());

        mockMvc.perform(get("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)));

        verify(customerService).listAllCustomers();


    }

    @Test
    public void testGetCustomerById() throws Exception {
        CustomerDTO customer = customerServiceImpl.listAllCustomers().getFirst();

        given(customerService.getCustomerById(any(UUID.class))).willReturn(Optional.of(customer));

        mockMvc.perform(get("/api/v1/customer/" + customer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(customer.getId().toString())))
                .andExpect(jsonPath("$.customerName", is(customer.getCustomerName())));

        verify(customerService).getCustomerById(uuidArgumentCaptor.capture());
        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());

    }

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerDTO customer = customerServiceImpl.listAllCustomers().getFirst();

        given(customerService.save(any(CustomerDTO.class))).willReturn(customerServiceImpl.listAllCustomers().get(1));

        // When setting the content must set the content type too or an HTTP Error Code 415 is returned.
        mockMvc.perform(post("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"));

        verify(customerService).save(customerArgumentCaptor.capture());
        assertThat(customer).isEqualTo(customerArgumentCaptor.getValue());

    }

    @Test
    public void testUpdateCustomer() throws Exception {
        CustomerDTO customer = customerServiceImpl.listAllCustomers().getFirst();

        given(customerService.put(any(UUID.class), any(CustomerDTO.class))).willReturn(Optional.of(customer));

        mockMvc.perform(put("/api/v1/customer/" + customer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isNoContent());

        verify(customerService).put(uuidArgumentCaptor.capture(), customerArgumentCaptor.capture());
        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(customer).isEqualTo(customerArgumentCaptor.getValue());

    }

    @Test
    public void testUpdateCustomerWithInvalidId() throws Exception {
        CustomerDTO customer = customerServiceImpl.listAllCustomers().getFirst();

        UUID randomUUID = UUID.randomUUID();

        mockMvc.perform( put("/api/v1/customer/" + randomUUID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));

        verify(customerService).put(uuidArgumentCaptor.capture(), customerArgumentCaptor.capture());
        assertThat(randomUUID).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(customer).isEqualTo(customerArgumentCaptor.getValue());
    }

    @Test
    public void testDelete() throws Exception {
        CustomerDTO customer = customerServiceImpl.listAllCustomers().getFirst();

        given(customerService.delete(any(UUID.class))).willReturn(Boolean.TRUE);

        mockMvc.perform(delete("/api/v1/customer/" + customer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(customerService).delete(uuidArgumentCaptor.capture());
        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());

    }

    @Test
    public void testDeleteWithInvalidId() throws Exception {

        CustomerDTO customer = customerServiceImpl.listAllCustomers().getFirst();

        UUID randomUUID = UUID.randomUUID();

        given(customerService.delete(any(UUID.class))).willReturn(Boolean.FALSE);

        mockMvc.perform(delete("/api/v1/customer/" + randomUUID))
                .andExpect(result -> assertInstanceOf(NotFoundException.class, result.getResolvedException()));

        verify(customerService).delete(uuidArgumentCaptor.capture());
        assertThat(randomUUID).isEqualTo(uuidArgumentCaptor.getValue());

    }

    @Test
    public void testPatch() throws Exception {
        CustomerDTO customer = customerServiceImpl.listAllCustomers().getFirst();

        Map<String, String> customerMap = new HashMap<>();
        customerMap.put("customerName", "New Name");

        given(customerService.patchById(any(UUID.class), any(CustomerDTO.class))).willReturn(Optional.of(customer));

        mockMvc.perform( patch( "/api/v1/customer/" + customer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerMap)))
                .andExpect(status().isNoContent());

        verify(customerService).patchById(uuidArgumentCaptor.capture(), customerArgumentCaptor.capture());
        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(customerMap.get("customerName")).isEqualTo(customerArgumentCaptor.getValue().getCustomerName());
    }

    @Test
    public void testPatchWithInvalidId() throws Exception {
        CustomerDTO customer = customerServiceImpl.listAllCustomers().getFirst();

        Map<String, String> customerMap = new HashMap<>();
        customerMap.put("customerName", "New Name");

        given(customerService.patchById(any(UUID.class), any(CustomerDTO.class))).willReturn(Optional.empty());

        mockMvc.perform( patch( "/api/v1/customer/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerMap)))
                .andExpect(result -> assertInstanceOf(NotFoundException.class, result.getResolvedException()));

        verify(customerService).patchById(any(UUID.class), any(CustomerDTO.class));


    }

    @Test
    public void getCustomerByIdNotFound() throws Exception {

        given(customerService.getCustomerById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/customer/" + UUID.randomUUID())).andExpect(status().isNotFound());

        verify(customerService).getCustomerById(any(UUID.class));
    }

    @Test
    public void testCustomerSave() throws Exception {
        CustomerDTO saveRecord = customerServiceImpl.listAllCustomers().getFirst();
        given(customerService.save(any(CustomerDTO.class))).willReturn(saveRecord);
        mockMvc.perform( post("/api/v1/customer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(saveRecord))).andExpect(status().isCreated());

        verify(customerService).save(any(CustomerDTO.class));

    }

    @Test
    void testCreateCustomerNullBeerName() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().build();

        given(customerService.save(any(CustomerDTO.class))).willReturn(customerServiceImpl.listAllCustomers().getFirst());

        mockMvc.perform(post("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateCustomerWhiteSpaceSpaceBeerName() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().build();
        beerDTO.setBeerName("   ");

        given(customerService.save(any(CustomerDTO.class))).willReturn(customerServiceImpl.listAllCustomers().getFirst());

        mockMvc.perform(post("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateCustomerEmptyBeerName() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().build();
        customerDTO.setCustomerName("");

        given(customerService.save(any(CustomerDTO.class))).willReturn(customerServiceImpl.listAllCustomers().getFirst());

        mockMvc.perform(post("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void showMockResult() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().build();
        customerDTO.setCustomerName("");

        given(customerService.save(any(CustomerDTO.class))).willReturn(customerServiceImpl.listAllCustomers().getFirst());

        MvcResult result = mockMvc.perform(post("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(1)))
                .andReturn();

        System.out.println(result.toString());
        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    public void testConstraintRequiredCustomerName() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().build();

        given(customerService.save(any(CustomerDTO.class))).willReturn(customerServiceImpl.listAllCustomers().getFirst());

        mockMvc.perform(post("/api/v1/customer")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testConstraintRequiredNonBlankCustomerName() throws Exception {
        CustomerDTO customerDTO = customerServiceImpl.listAllCustomers().getFirst();
        customerDTO.setCustomerName("");

        given(customerService.save(any(CustomerDTO.class))).willReturn(customerDTO);

        mockMvc.perform(post("/api/v1/customer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(1)));
    }

}


