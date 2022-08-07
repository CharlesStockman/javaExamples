package com.student.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * A class that show examples of Spring Content Negotiation
 */
public class StudentClientContentNegotiation {

    /**
     * Trigger the service
     * @return
     */
    public void serviceTrigger() {
        String url = "http://localhost:8081/college/student/1";
        ResponseEntity responseEntity = new RestTemplate().exchange(url, HttpMethod.GET,
            new HttpEntity<String>(getHeaders()), String.class, 1);
        printResponseEntity("Get an student by id ", responseEntity);
    }

    /**
     * Create the headers for the HTTP Request.
     * 
     * Note the code selects the frist accpet value and use that to return the data
     * For example "accept: application/xml, applicaiton/json" then xml will always 
     * be return -- this may different later, but for now its fact.
     * 
     * If there  no accept key then the server determines what to send back
     * 
     * @return The headers for the http request1
     */
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/json, application/xml");
        return headers;
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
