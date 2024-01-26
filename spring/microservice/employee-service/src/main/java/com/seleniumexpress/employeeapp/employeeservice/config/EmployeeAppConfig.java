package com.seleniumexpress.employeeapp.employeeservice.config;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import feign.Logger;

@Configuration
public class EmployeeAppConfig {

    @Value("${addressservice.base.url}")
    private String addressBaseURL;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() { return new RestTemplate(); }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() { return new RestTemplateBuilder(); }

    @Bean WebClient WebClient() {
        WebClient webClient = WebClient.builder().baseUrl(addressBaseURL).build();
        return webClient;
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
