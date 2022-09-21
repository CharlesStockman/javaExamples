package com.student.microservice.service;

import java.util.Random;
import java.math.BigDecimal;

/**
 * Business Logic for Student Fines 
 */
public class FinesService {

    /**
     * Get the fines that the student owes
     * 
     * @param studentId -- The primary key for the student
     * @return The fine in English currency between 0 and 10 dollars
     */
    public double getFine(int studentId) {
        double leftLimit = 0.0;
        double rightLimit = 10.0;

        double doubleGenerated = leftLimit + new Random().nextDouble() + ( rightLimit - leftLimit);
        BigDecimal bigDecimal = new BigDecimal(doubleGenerated).setScale(2);
        return bigDecimal.doubleValue();
        
    }
}
