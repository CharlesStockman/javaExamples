package com.example.springAop.model;


import lombok.Data;

@Data
public class EmployeeHours {

    String functionName;
    String employeeName;
    Boolean hasArrived;
    Integer hoursWorked;
    Boolean approved;

    public void displayData() {
        System.out.printf("%15s%10s%6s%d%6s\n", functionName, employeeName, hasArrived, hoursWorked, approved);

    }


}