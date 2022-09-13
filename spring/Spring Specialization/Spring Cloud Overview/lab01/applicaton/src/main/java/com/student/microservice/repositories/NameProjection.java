package com.student.microservice.repositories;

import org.springframework.data.rest.core.config.Projection;

import com.student.microservice.core.Student;

/**
 * Create a project to return the full name from the Student Table
 */
 @Projection( name = "person", types = Student.class)
 public interface NameProjection {

    // First Name of the person
    public String getFirstName();

    // Family name of the person
    public String getSurname();

    // The primary key in the database
    public Integer getId();
}
