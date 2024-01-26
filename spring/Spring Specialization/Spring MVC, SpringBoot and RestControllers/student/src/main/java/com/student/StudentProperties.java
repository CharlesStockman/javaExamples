package com.student;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * The purpose of this class is to show to how retrieve multiple variables from a properties or YAML
 */
@Data
@ConfigurationProperties(prefix="student")
@EnableConfigurationProperties(value = StudentProperties.class)
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