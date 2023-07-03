package com.seleniumexpress.employeeapp.employeeservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="employee")
@Data
public class Employee {

    // persistence provider must assign primary key for the entity using a database identity column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="bloodgroup")
    private String bloodGroup;
}
