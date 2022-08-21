package com.student.repositories;

/**
 * Create a project to return the full name from the Student Table
 */
public interface NameProjection {

    // First Name of the person
    public String getFirstName();

    // Family name of the person
    public String getSurname();

    // The primary key in the database
    public Integer getId();
}
