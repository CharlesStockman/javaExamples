package com.seleniumexpress.employeeapp.employeeservice.service;


import com.seleniumexpress.employeeapp.employeeservice.entity.Employee;
import com.seleniumexpress.employeeapp.employeeservice.repository.AddressRestService;
import com.seleniumexpress.employeeapp.employeeservice.repository.EmployeeRepository;
import com.seleniumexpress.employeeapp.employeeservice.response.AddressResponse;
import com.seleniumexpress.employeeapp.employeeservice.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.cloud.client.discovery.DiscoveryClient;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    private final DiscoveryClient discoveryClient;

    private final LoadBalancerClient loadBalancerClient;

    private final AddressRestService addressRestService;

    public EmployeeService(
            EmployeeRepository repository,
            ModelMapper modelMapper,
            DiscoveryClient discoveryClient,
            LoadBalancerClient loadBalancerClient,
            AddressRestService addressRestService) {
        this.employeeRepository = repository;
        this.modelMapper = modelMapper;
        this.discoveryClient = discoveryClient;
        this.loadBalancerClient = loadBalancerClient;
        this.addressRestService = addressRestService;
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<EmployeeResponse> responses = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        for ( Employee employee : employees ) {
            EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
            employeeResponse.setAddressResponse(addressRestService.getAddressByEmployeeId(employee.getId()));
            responses.add(employeeResponse);
        }

        return responses;
    }

    public EmployeeResponse getEmployeeById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        AddressResponse addressResponse = addressRestService.callToAddressServiceUsingFeign(id);
        employeeResponse.setAddressResponse(addressResponse);

        return employeeResponse;
    }



    /**
     * Print out information about each instance of address service in the Eureka Server
     * Written since it was intermediate code -- Used
     */
    public String showInstances() {
        List<ServiceInstance> instances = discoveryClient.getInstances("address-service");
        StringBuilder buffer = new StringBuilder();
        for (ServiceInstance instance : instances) {
            buffer.append(instance.toString());
            buffer.append("---------------------------------");
        }

        return buffer.toString();
    }

    /**
     * Get a random Address Service from Eureka and display information -- Use for instructional purposes
     */
    public String getRandomService() {
        ServiceInstance instance = loadBalancerClient.choose("address-service");
        return instance.toString();
    }
}
