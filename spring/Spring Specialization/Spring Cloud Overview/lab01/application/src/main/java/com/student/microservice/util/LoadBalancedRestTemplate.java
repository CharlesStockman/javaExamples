package com.student.microservice.util;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Create a Rest Template that does load balancing
 */
@Component
public class LoadBalancedRestTemplate {

    //
    RestTemplate restTemplate = null;

    /**
     * @return A load balanced rest template
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        if ( restTemplate == null ) restTemplate = new RestTemplate();
        return restTemplate;
    }
}
