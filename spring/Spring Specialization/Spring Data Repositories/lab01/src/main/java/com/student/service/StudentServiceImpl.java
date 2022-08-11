package com.student.service;

import java.util.Collection;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

	/**
	 * Retrieves the student by department
	 * 
	 * When a do a major refactor I move the query code form the service to the dao.
	 * 
	 * @param department    The specific department where the students will be retrieved
	 * 
	 * @return A list of the students from the department
	 */
	@Override
	public Collection<Student> getStudentsByDepartment(String department) {
		TypedQuery query = entityManager.createQuery(
			"select student " +
			"from Student student " + 
			"where student.dept = :dept", Student.class);

		query.setParameter("dept", department);
		return query.getResultList();
	}


 
}
