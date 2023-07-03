package com.seleniumexpress.addressapp.addressservice.service;

import com.seleniumexpress.addressapp.addressservice.entity.Address;
import com.seleniumexpress.addressapp.addressservice.entity.AddressWithEmployeeId;
import com.seleniumexpress.addressapp.addressservice.repository.AddressRepository;
import com.seleniumexpress.addressapp.addressservice.repository.AddressWithEmployeeIdRepository;
import com.seleniumexpress.addressapp.addressservice.response.AddressResponse;
import com.seleniumexpress.addressapp.addressservice.response.EmployeeToAddressMapResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddressService {

    private ModelMapper modelMapper;

    private AddressRepository addressRepository;

    private AddressWithEmployeeIdRepository addressWithEmployeeIdRepository;

    AddressService(AddressRepository repository, AddressWithEmployeeIdRepository repository2 , ModelMapper modelMapper) {
        this.addressRepository = repository;
        this.addressWithEmployeeIdRepository = repository2;
        this.modelMapper = modelMapper;
    }

    public AddressResponse findAddressByEmployeeId(int employeeId) {
        Address address = addressRepository.findAddressByEmployeeId(employeeId);
        AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);

        return addressResponse;
    }

    public EmployeeToAddressMapResponse getAllAddresses() {
        Map<Integer, AddressResponse> addressesMap = new HashMap<>();
        for ( AddressWithEmployeeId address : addressWithEmployeeIdRepository.findAll()) {
            addressesMap.put(address.getId(), modelMapper.map(address, AddressResponse.class));
        }

        EmployeeToAddressMapResponse employeeToAddressMap = new EmployeeToAddressMapResponse();
        employeeToAddressMap.setData(addressesMap);

        return employeeToAddressMap;
    }
}
