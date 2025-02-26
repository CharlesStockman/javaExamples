package com.example.springAop.model;


import lombok.Data;
import lombok.Value;

@Value
public class EmployeeHours {

    String functionName;
    String employeeName;
    Boolean hasArrived;
    Integer hoursWorked;
    Boolean approved;

    public void displayData() {
        System.out.printf("%15s%10s%6s%6s%6s\n", functionName, employeeName, hasArrived, hoursWorked, approved);
    }

    public static String getFormatString() { return "%15s%10s%10s%10s%10s\n"; }


}