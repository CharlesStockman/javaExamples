package com.student;

import com.student.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/* load entire application context for Spring Boot application and create integration test */
/* Useful when
/* WebEnvironment can be defined port, random port, none or Mock ( Mock Servlet Environment */
@WebMvcTest(StudentController.class)
//@AutoConfigureMockMvc  // Creates a MockMvc Bean
class AddressServiceApplicationTests {

	// MockMvc is configured to accept request and return responses;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void testEndPointMsg() throws Exception {
		mockMvc.perform(
						MockMvcRequestBuilders.
								get("/test").
								accept(MediaType.APPLICATION_JSON)).
				andExpect(MockMvcResultMatchers.status().isOk());

	}
}
