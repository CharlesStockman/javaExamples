package com.seleniumexpress.addressapp.addressservice.repository;

import com.seleniumexpress.addressapp.addressservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(nativeQuery = true, value="select address.id, address.lane1, address.lane2, address.state, address.zip from address where address.employee_id = :employeeId")
    Address findAddressByEmployeeId(@Param("employeeId") Integer employeeId);
}
