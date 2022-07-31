package com.student.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.student.dao.StudentDao;
import com.student.core.Student;

import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import org.hibernate.resource.beans.container.internal.NoSuchBeanException;;

public class StudentServiceTest {
	
	private ApplicationContext context;
	private StudentService service;

	@BeforeEach
	void setUp() {
		  context = new ClassPathXmlApplicationContext("beans.xml");
		  assertNotNull(context, "Spring Application Context is null");

		  service = context.getBean("service", StudentService.class);
		  assertNotNull(service, "Spring Service Bean is null -- " + service.getClass().getName());
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

		StudentDao dao = null;
		try {
			dao = context.getBean("dao", StudentDao.class);
		} catch(NoSuchBeanException exception) {
			assertNotNull(dao, "The Dao Bean can not be retreived from the Application Context");
		}

	}

	@Test
	void testDaoIsCreatedInsideService() {
		StudentServiceImpl service = context.getBean("service", StudentServiceImpl.class);
		try {
			service.get(1L);
		} catch( NullPointerException nullPointerException ) {
			throw new NullPointerException("The service cannot inject the Dao Bean");
		}
	}

	@Test
	void testGetFirstNStudents() {
		Collection<Student> students = service.getFirstNStudents();
		assertTrue( students.size() == 2, "The service using method getFristNStudents");
	}

	
}
