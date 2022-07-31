package com.student.controller;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentControllerTest {

    /**
     * Spring MVC Test instance
     */
    private MockMvc mockMvc;
    
    /**
     * A routine that creates a minimal MVC environment ( Dispatcher Server etc.. )
     * and allow the user to provide the controllers to be tested.
     */
    //@BeforeEach
    //public void setup() {
    //    this.mockMvc = MockMvcBuilders.standaloneSetup(new StudentController()).build();
    //}

    /**
     * Perform a get operation and verfy that a request is returned and the content is "Hello World"
     * 
     * @throws Exception Current unknown why
     */
    //@Test
    //public void testDefaultIndex() throws Exception {
    //    this.mockMvc.perform(get("/student/msg")).andExpect(status().isOk()).andExpect(content().string("Hello from StudentController"));
    //}
}
