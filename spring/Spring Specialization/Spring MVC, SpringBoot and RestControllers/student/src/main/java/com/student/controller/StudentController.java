package com.student.controller;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.student.core.Student;
import com.student.service.StudentService;
/**
 * The controller will associate a URL with a function.  When the code 
 * is exuected it will transform the request into a Java Object and 
 * create a repsonse.  The code will inject services to perform the 
 * fucntion needed to perform the business loigc.
 * 
 * Currently we are retrieving the properties by using @ConfigurationProperties/
 * EnableConfigurations which will load all the properites into StudentProperites 
 * value
 * 
 * Another way is to inject the class that we have created to hold our properties 
 * (StudentProperites.class) and then call the getters to retrieve the properties
 * 
 * Don't forget the EnvironmentBean can be injected into the class
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Value("${greeting}")
    private String message;

    @Value("${student.max}")
    private Integer firstNStudents;

    @Autowired
    private StudentService studentService;


    @GetMapping("/msg")
    public String helloWorld(@RequestHeader("user-agent") String userAgent) {
        return "Hello from StudentController using " + userAgent; 
    }

    @GetMapping("/msg2")
    public String messageFromProperties() {
        return message;
    }

    @GetMapping
    public String getAllStudents() {
        return studentService.getAllStudents().toString();
    }

    @GetMapping("/showFirstNStudents")
    public String showFirstNStudents() {
        return "The first " + firstNStudents + " are students are "+ 
            studentService.getFirstNStudents(firstNStudents);
    }

    @GetMapping("/returnStudentFromDepartment")
    public String showStudentsFromDepartment() {
        return "The students from the department are: " + studentService.getStudentsFromDeparment();  
    }
    
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") long id) {
        return studentService.get(id);
    }

    @GetMapping("/single")
    public Student getSingleStudent(@RequestParam("id") Optional<Long> optional ) {
        return studentService.get(optional.orElse(1L));
    }

    @GetMapping("/search/{department}")
    public Collection<Student> getStudentsPerDepartment(@PathVariable("department") String department, @RequestParam("name") Optional<String> optional) {
        return studentService.getAllStudentsInDepartments(department, optional.orElse(""));
    }

    @PostMapping
	public ResponseEntity<String> addStudent(@RequestBody Student student ) {
        studentService.addStudent(student);
        if ( student.getId() > 0 ) {
            URI uri = URI.create("localhost:8081/college/student/" + student.getId());
            System.out.println("Charles Stockman : " + uri.toString());
            return ResponseEntity.accepted().location(uri).build();
        } else 
            return ResponseEntity.badRequest().build();
    }

    //RedirectView home() {
    //    return new RedirectView("college/student/msg2");
    //} 
}
