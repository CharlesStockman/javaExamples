package com.student.microservice.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.student.microservice.core.Student;
import com.student.microservice.repositories.StudentRepository;
import com.student.microservice.util.LoadBalancedRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.client.RestTemplate;

@Named
@RibbonClient(name="ribbonService")
public class StudentServiceImpl implements StudentService {
 
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private RestTemplate restTemplate;

	@Inject
	private StudentRepository studentRepository;
	 
	@Override
	public Student get(long id) {
		System.out.println("*************************************************");
		Student student = entityManager.find(Student.class, id);
		System.out.println(student.toString());
		System.out.println("Do not pay yet.  Warning only -- the find is " + getFeeForStudent(1));
		System.out.println("*************************************************");

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

	/**
	 * @return the fee that the student owes
	 */
	public double getFeeForStudent(long studentId) {
		String url = "http://ribbonService/1";
		double  debt = restTemplate.getForObject(url, Double.class);
		return debt;
	}
}
