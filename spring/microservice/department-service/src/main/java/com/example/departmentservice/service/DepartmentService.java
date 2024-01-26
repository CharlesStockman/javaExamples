package com.example.departmentservice.service;

import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.response.DepartmentResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository = null;

    private ModelMapper modelMapper;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentResponse> getAllDepartments() {

        List<Department> departments = departmentRepository.findAll();
        for ( Department department : departments ) {
             DepartmentResponse departmentResponse = modelMapper.map(department, DepartmentResponse.class);
        }

        return null;


    }
}
