package com.student;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * The purpose of this class is to store values retrieved from *.properties and *.yml
 */
@Data
@ConfigurationProperties(prefix="student")
public class StudentProperties {

    // A value to select the first number of students from the lsit
    private Integer max;

    // The department used to select the student 
    private String department;

    /**
     * Display the values from the configuration file
     */
    @PostConstruct
    public void display() {
        System.out.println("Charles Stockman -------------------  ");
        System.out.println(toString());
    }
 }