package com.example.SpringSecuirty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

// With this dependency automatically, you will have a Username/Password Login Form
// For the default Username/Password Form a default password is supplied look in Spring Boot logs
//		User is the name of the default user
// Look for the log messages:
//		Using generated security password: af428ece-629d-4d87-b0cd-1f79aad1bb78
//		This generated password is for development use only. Your security configuration must be updated before running your application in production.
// Opens the new tab then it automatically goes to the application skipping the login page (Session Management)
// Can do a logout: URL is http://<ip address>:<port>/logout -- Will take you to a form that will ask if you want to log out.
// @EnableWebSecurity
//     1. Enables Spring Security in a Web Application
//     2. Imports the WebSecurityConfiguration Class
//     3. What kind of security is being implemented can have Flux and Socket too.
@SpringBootApplication
@EnableWebSecurity
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
