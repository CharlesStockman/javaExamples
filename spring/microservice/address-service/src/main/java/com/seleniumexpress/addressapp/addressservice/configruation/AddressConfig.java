package com.seleniumexpress.addressapp.addressservice.configruation;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.rmi.MarshalledObject;

@Configuration
public class AddressConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
