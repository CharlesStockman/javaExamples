package com.student.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * A class that show examples of Spring Content Negotiation
 */
public class StudentClientContentNegotiation {

    /**
     * An example of using the rest template to retrieve the Response Entity
     * containing the first student as the body of the response.
     */
    public void serviceTrigger() {
        String url = "http://localhost:8081/college/student/1";
        ResponseEntity responseEntity = new RestTemplate().exchange(url, HttpMethod.GET,
            new HttpEntity<String>(getHeaders()), String.class, 1);
        printResponseEntity("Get an student by id ", responseEntity);
    }

    /**
     * Create the headers for the HTTP Request.
     * <p>
     *  Note the code selects the first value from the accept keyword.  The data will be returned in that format
     *  For example "accept: application/xml, application/json" then xml will always used as the response format
     * </p>
     * If there is no accept key then the server determines what to send back
     * 
     * @return The headers for the http request
     */
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/json, application/xml");
        return headers;
    }



    /**
     * Dispaly all the information about the Response Entity
     * 
     * @param action -- A description of why this ResponseEntity being displayed
     * @param responseEntity -- Display information about response returned from the Dispatcher
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
