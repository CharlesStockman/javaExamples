package com.seleniumexpress.addressapp.addressservice.repository;

import com.seleniumexpress.addressapp.addressservice.entity.AddressWithEmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressWithEmployeeIdRepository extends JpaRepository<AddressWithEmployeeId, Integer> {
}