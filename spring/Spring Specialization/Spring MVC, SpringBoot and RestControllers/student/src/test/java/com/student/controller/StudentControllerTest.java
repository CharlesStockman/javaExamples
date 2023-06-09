package com.student.controller;

import com.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;

//@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, )
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    /**
     * Spring MVC Test instance
     */
    private MockMvc mockMvc;

    @MockBean
    StudentService studentService;
    
    /**
     * A routine that creates a minimal MVC environment ( Dispatcher Server etc.. )
     * and allow the user to provide the controllers to be tested.
     */
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new StudentController()).build();
    }

    //                 //andExpect(MockMvcResultMatchers.status().isOk());
    @Test
    public void testEndPointMsg() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.
                                get("/student/msg2").
                                accept(MediaType.APPLICATION_JSON)).
                andDo(MockMvcResultHandlers.print());

    }

    /**
     * Perform a get operation and verify  that a request is returned and the content is "Hello World"
     * 
     * @throws Exception Current unknown why
     */
//    @Test
//    public void testDefaultIndex() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/student/msg")).
//                andExpect(status().isOk()).
//                andExpect(content().string("Hello from StudentController"));
//    }
}
