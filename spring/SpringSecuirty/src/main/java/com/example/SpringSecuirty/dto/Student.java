package com.example.SpringSecuirty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    private int id;
    private String name;
    private int marks;
}
