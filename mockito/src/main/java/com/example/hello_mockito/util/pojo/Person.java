package com.example.hello_mockito.util.pojo;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Person {
    long id;
    String firstName;
    String secondName;
    LocalDate dateOfBith;
}
