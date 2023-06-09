package com.student.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.student.core.Student;

/**
 * A client that will communicate with the server using RestTemplate
 */
public class StudentClientRestTemplate {

    /**
     * Using an Http Post Verb to add a Student
     */
    public void addStudent() {

        // Create the Student and provide the URL for adding it.
        Student student = new Student("Susan", "Doubtfire", "French", 75.00);
        String url = "http://localhost:8081/college/student";

        // Creates an Entity with headers and the student data
        HttpEntity<Student> studentData = new HttpEntity<Student>(student, generateHeaders());
        printHttpEntity("Creating a post object ======================", studentData);

        // Add the data to the database and display the ResponseEntity
        // The data will be sent to the backend using a post command.
        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(
                url, studentData, String.class);
        printResponseEntity("postForEntity =====================", responseEntity);

        // Retrieve the new data using getForEntity that returns the ResponseEntity with the Student instance
        // that was added to the database in the previous code.
        ResponseEntity<Student> studentResponse = new RestTemplate().getForEntity(
            "http://" + responseEntity.getHeaders().getLocation(), Student.class);
        printResponseEntity("getForEntity ================== ", studentResponse);

        // Negative Attempt -- To explore what happens if there is a failure
        // Interesting -- If an exception is thrown from the postForEntry the exception will 
        // contain the status code and more.  If the request fails it will not return a Response
        // Entity but throw an exception
        Student studentNegativeStudent = new Student("", "", "", 0.0);
        HttpEntity<Student> negativeEntity = new HttpEntity<>(studentNegativeStudent, generateHeaders());
        ResponseEntity<?> negativeResponseEntity = null;
        try {
            System.out.println("Charles Stockman : Made it here");
            negativeResponseEntity = new RestTemplate().postForEntity(
                url, negativeEntity, String.class);
        } catch (HttpClientErrorException exception ) {
            System.out.println("==============  Displaying the Response Repository for a negative outcome ");
            System.out.println("The Status Code is " + exception.getStatusCode());
            System.out.println("The message is " + exception.getMessage());
        }     
    }
    
    /**
     * Creates headers for the HttpEntity calls
     */
    private HttpHeaders generateHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("content-type", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);

        return httpHeaders;
    }

    /**
     * A utility function to print the HttpEntity ( represents an HTTP Request or Response ) consisting of headers
     * and a body.
     * 
     * @param action                A description of what is being executed.
     * @param entity                The instance that will have its data displayed
     */
    private void printHttpEntity(String action, HttpEntity<?> entity ) {
        System.out.println("The action is " + action);
        System.out.println("The headers are " + entity.getHeaders());
        System.out.println("The body is     " + entity.getBody());
        System.out.println("The class is    " + entity.getClass());
    }

    /**
     * Dispaly all the information about the a Response Entitty
     * 
     * @param action -- A description of why this ResponseEntity being displayed
     * @param responseEntity -- Display informatin about repsonse returned from the Dispatcher
     */
    private void printResponseEntity( String action, ResponseEntity<?> responseEntity ) {
        
        System.out.println("The action is " + action);
        System.out.println("Response Entity is " + responseEntity.toString());
        System.out.println("Response Entity Status Code Value is " + responseEntity.getStatusCodeValue());
        System.out.println("Repsonse Entity Status Code Value is " + responseEntity.getStatusCode());
        System.out.println("Response Entity Class             is " + responseEntity.getClass());
        System.out.println("Response Entity Headers           is " + responseEntity.getHeaders());
        System.out.println("Response Entity Body              is " + responseEntity.getBody());

    }
}
