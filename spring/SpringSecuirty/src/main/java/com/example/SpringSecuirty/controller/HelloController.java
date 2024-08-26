package com.example.SpringSecuirty.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Behind the scene, every controller gets converted to a servet (tomcat is a servlet container).
// Front End Controller is the dispatcher servlet.
// Goes from the Filter Chain to the Front End Controller to the Controller.
//
// Filter Chain -- Spring Security adds its own filters examples below (Chain of Command Pattern)
//      SecurityContextPersistenceFilter LogoutFilter UserNamePasswordAuthenticateFilter DefaultLoginPageGeneratingFilter,
//      RequestCacheAwareFilter etc.
// Can customize filters
// Can change the request/response
//
// Session
//    For request the session id (JSESSIONID) is part of the cookie
//
// Can get the username/password from the application.properties
// Postman : Authorization ( Type: Basic Auth )
@RestController
public class HelloController {


    @GetMapping("/")
    public String greet(HttpServletRequest request) {
        return "Hello Secure World for session id " + request.getSession().getId();
    }
}
