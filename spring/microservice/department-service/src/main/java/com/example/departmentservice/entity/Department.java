package com.example.departmentservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String departmentName;

    @Column(name="employee_id")
    private Integer employeeId;



}
