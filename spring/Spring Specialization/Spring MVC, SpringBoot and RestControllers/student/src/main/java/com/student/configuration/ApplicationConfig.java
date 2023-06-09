package com.student.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.student.dao.StudentDao;
import com.student.dao.StudentDaoImpl;
import com.student.service.StudentService;
import com.student.service.StudentServiceImpl;

/** 
 * A class used to create the application beans for the SpringFramework
*/
@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    /**
     * Create the Dao Bean to access the Student Database
     * 
     * @return A StudentDao Bean
     */
    @Bean
    public StudentDao studentDaoImpl() {
        StudentDao dao = new StudentDaoImpl();
        return dao;
    }

    /**
     * Create the service which will peform  the business logic for the Student
     * 
     * @return a StudentService Bean
     *
     */
    @Bean
    public StudentService studentServiceDaoImpl() {
        StudentServiceImpl service = new StudentServiceImpl();        
        return (StudentService)service;   
    }
    
}
