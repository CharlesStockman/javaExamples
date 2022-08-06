package com.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.student.client.StudentClient;

@SpringBootApplication
@EnableConfigurationProperties(value = StudentProperties.class)
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
		StudentClient client = new StudentClient();
		client.addStudent();
	}

}
