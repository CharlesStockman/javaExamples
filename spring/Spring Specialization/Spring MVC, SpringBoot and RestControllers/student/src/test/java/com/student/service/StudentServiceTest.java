package com.student.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.student.configuration.ApplicationConfig;
import com.student.core.Student;
import com.student.dao.StudentDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=ApplicationConfig.class)
public class StudentServiceTest {
	
	@Autowired
	private StudentService service;

	@Autowired
	private StudentDao dao;;

	@BeforeEach
	void setUp() {
	}
	
 
	@Test
	void testGetOneStudent() {
		Student student = service.get(1L);
		assertNotNull(student, "Could not get a student from the service");
	}
	
	@Test
	void tesGetAll() {
		Collection<Student> students = service.getAllStudents();
		assertTrue( students.size() == 3, "The students collection should have three eleements");
	}

	@Test
	void testDaoBeanIsCreated() {
		assertNotNull(dao, "The Dao Bean can not be retreived from the Application Context");
	}

	@Test
	void testGetFirstNStudents() {
	 	Collection<Student> students = service.getFirstNStudents(2);
	 	assertTrue( students.size() == 2, "The service using method getFristNStudents has failed");
	}

	//@Test
	//void testGetStudentsFromDepartment() {
	//	Collection<Student> students = service.getStudentsFromDeparment();
	//	assertTrue( students.size() == 2, "The service using methods getStudentsFromDepartment has failed");
	//}
}
