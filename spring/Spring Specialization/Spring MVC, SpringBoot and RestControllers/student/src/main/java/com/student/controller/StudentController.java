package com.student.controller;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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
 * is executed it will transform the request into a Java Object and
 * create a response.  The code will inject services to perform the
 * function needed to perform the business logic.
 * 
 * Currently, we are retrieving the properties by using @ConfigurationProperties/
 * EnableConfigurations which will load all the properties into StudentProperties
 * value
 * 
 * Another way is to inject the class that we have created to hold our properties 
 * (StudentProperties.class) and then call the getters to retrieve the properties
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


    /**
     * An example that shows how create a get rest point and retries a value from the request's header.
     *
     * @param userAgent         A string that lets you know the OS and Browser Type/Version
     *
     * @return                  A stirng with the information from the key "user-agent"
     */
    @GetMapping("/msg")
    public String helloWorld(@RequestHeader("user-agent") String userAgent) {
        return "Hello from StudentController using " + userAgent; 
    }

    /**
     * Retrieve a value from the properties file and returns to the user.
     *
     * @return  The string from the property file
     */
    @GetMapping("/msg2")
    public String messageFromProperties() {
        return message;
    }

    /**
     * Returns all students found in the data repository
     *
     * @return The list of student from the data repository or an empty list
     */
    @GetMapping
    public String getAllStudents() {
        return studentService.getAllStudents().toString();
    }

    /**
     * @return a subset of the student start with first student
     *
     * firstNStudents is a private variable initialized using @Value )
     *
     */
    @GetMapping("/showFirstNStudents")
    public String showFirstNStudents() {
        return "The first " + firstNStudents + " are students are "+ 
            studentService.getFirstNStudents(firstNStudents);
    }

    /**
     * Show the students and what
     * @return
     */
    @GetMapping("/returnStudentFromDepartment")
    public String showStudentsFromDepartment() {
        return "The students from the department are: " + studentService.getStudentsFromDeparment();  
    }

    /**
     * Use the Get Rest End Point to return a specific student
     *
     * @param       id              The identification of the student
     *
     * @return      Either an empty list or the student's information
     */
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") long id) {
        return studentService.get(id);
    }

    /**
     * Use the Get Rest Endpoint to return a specific student, but use optional so a default value can be provided.
     *
     * @param       optional              The identification of the student
     *
     * @return      Either an empty list or the student's information
     *
     */
    @GetMapping(path="/single", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Student getSingleStudent(@RequestParam("id") Optional<Long> optional ) {
        return studentService.get(optional.orElse(1L));
    }

    /**
     * Retrieve the student's information using both the student's id and the department id.
     *
     * @param department                The department that the student is associated with
     * @param optional                  The name of the student
     *
     * @return
     */
    @GetMapping("/search/{department}")
    public Collection<Student> getStudentsPerDepartment(@PathVariable("department") String department, @RequestParam("name") Optional<String> optional) {
        return studentService.getAllStudentsInDepartments(department, optional.orElse(""));
    }

    /**
     * Add a student to the database
     *
     * @param student           Information about a student
     * @return A Response Entity that tell if the addition succeeded or failed
     */
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
