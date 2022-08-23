package com.student.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.student.core.Student;
import com.student.repositories.StudentRepository;
 
@Named
public class StudentServiceImpl implements StudentService {
 
	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private StudentRepository studentRepository;
	 
	@Override
	public Student get(long id) {
		Student student = entityManager.find(Student.class, id);
		System.out.println(student.toString());
		return student;
	}

	@Override
	public Collection<Student> getAllStudents() {
		javax.persistence.Query query = entityManager.createQuery("From Student");
		Collection<Student> students = (Collection<Student>)query.getResultList();
		return students;
 	}

	@Override
	public Collection<Student> getStudentsByDepartment(String department) {
		TypedQuery query = entityManager.createQuery(
			"select student " +
			"from Student student " + 
			"where student.dept = :dept", Student.class);

		query.setParameter("dept", department);
		return query.getResultList();
	}

	@Override
	public Collection<Student> getStudentsWhoPaidExactFee(double fee) {
		Collection<Student> students = studentRepository.findByFees(fee);
		return students;
	}

	@Override
	public void insertStudent(Student student) {
		studentRepository.save(student);
	} 
}
