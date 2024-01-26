package com.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.student.client.StudentClientContentNegotiation;
import com.student.client.StudentClientRestTemplate;

/**
 * The purpose is to provide examples of the Spring framework to refer to in the future.
 */
@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);

		StudentClientContentNegotiation client2 = new StudentClientContentNegotiation();
		client2.serviceTrigger();
	}

}
