package com.seleniumexpress.employeeapp.employeeservice.repository;

import com.seleniumexpress.employeeapp.employeeservice.fieignClient.AddressClient;
import com.seleniumexpress.employeeapp.employeeservice.response.AddressResponse;
import com.seleniumexpress.employeeapp.employeeservice.response.EmployeeResponse;
import com.seleniumexpress.employeeapp.employeeservice.response.EmployeeToAddressMapResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

/**
 * A class that will access the Address Service to get the address for each employee
 */
@Repository
public class AddressRestService {

    private final RestTemplate restTemplate;

    private WebClient webClient;

    private final AddressClient feignClient;

    private Map<Integer, AddressResponse> employeeToAddressMap = null;

    public AddressRestService(RestTemplate restTemplate, WebClient webClient, AddressClient feignClient) {
        this.restTemplate = restTemplate;
        this.webClient = webClient;
        this.feignClient = feignClient;
    }

    /**
     * Example of making a rest call using a WebClient
     *
     * Gets the Address information using the employee id.
     *
     */
    public AddressResponse callToAddressServiceUsingWebClient(int id ) {
        AddressResponse addressResponse = webClient.
                get().uri("/address/" +id).
                retrieve().
                bodyToMono(AddressResponse.class).
                block();

        return addressResponse;

    }

    /**
     * Example of making a rest call using rest template
     *
     * Gets the address for the employee using the id.
     */
    public AddressResponse callToAddressServiceUsingRestTemplate(int id) {
        AddressResponse addressResponse = restTemplate.getForObject(
                "http://ADDRESS-SERVICE/address-app/api/address/{id}",
                AddressResponse.class,
                id);

        return addressResponse;
    }

    /**
     * Example of making a rest call using FeignClient
     *
     * Get the address for the employee using the id;
     */
    public AddressResponse callToAddressServiceUsingFeign(int id) {
        AddressResponse response = feignClient.getAddressByEmployeeId(id).getBody();
        return response;
    }

    /**
     * Retrieve the EmployeeToAddressMap
     */
    private void getAllAddresses() {
        synchronized(this) {
            if (employeeToAddressMap == null)
                employeeToAddressMap = feignClient.getAllAddresses().getBody().getData();
        }
    }

    /**
     * Get all the addresses for each employee with one rest call
     */
    public AddressResponse getAddressByEmployeeId(int id) {
        getAllAddresses();
        AddressResponse response = employeeToAddressMap.get(id);
        return response;
    }
}
