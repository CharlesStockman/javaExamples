package com.student.service;

import java.util.Collection;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.student.core.Student;
 
@Named
public class StudentServiceImpl implements StudentService {
 
	@PersistenceContext
	private EntityManager entityManager;
	 
	@Override
	public Student get(long id) {
		Student student = entityManager.find(Student.class, 1L);
		System.out.println(student.toString());
		return student;
	}

	@Override
	public Collection<Student> getAllStudents() {
		javax.persistence.Query query = entityManager.createQuery("From Student");
		Collection<Student> students = (Collection<Student>)query.getResultList();
		return students;
 	}

 
}
