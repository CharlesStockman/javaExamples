package com.seleniumexpress.employeeapp.employeeservice.fieignClient;

import com.seleniumexpress.employeeapp.employeeservice.config.EmployeeAppConfig;
import com.seleniumexpress.employeeapp.employeeservice.response.AddressResponse;
import com.seleniumexpress.employeeapp.employeeservice.response.EmployeeToAddressMapResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Address-Service", path="/address-app/api", configuration = EmployeeAppConfig.class)
public interface AddressClient {

    @GetMapping("/address/{id}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("id") int id);

    @GetMapping("/address/")
    public ResponseEntity<EmployeeToAddressMapResponse> getAllAddresses();
}
