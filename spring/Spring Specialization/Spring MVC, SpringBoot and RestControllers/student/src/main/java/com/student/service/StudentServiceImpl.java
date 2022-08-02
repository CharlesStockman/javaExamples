package com.student.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.student.core.Student;
import com.student.dao.StudentDao;

public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao studentDao;

	// Creates the list of students from a specified department.
	@Value("#{studentDaoImpl.getAll().?[dept.equals('${student.department}')]}")
	private Collection<Student> departments = new ArrayList<Student>();
	
	@Override
	public Student get(long id) {
		return studentDao.getOne(id);
	}

	@Override
	public Collection<Student> getAllStudents() {
		return studentDao.getAll();
	}

	@Override
	public Collection<Student> getFirstNStudents(Integer firstNStudents) {
		List<Student> students = new ArrayList<Student>(studentDao.getAll()).stream().limit(firstNStudents).toList();
		return (Collection<Student>)students;

	}

	/**
	 * Get the students from the specified department
	 * 
	 * @return A list of students fromm the specified department
	 */
	@Override
	public Collection<Student> getStudentsFromDeparment() {
		return departments;
	}

	@Override
	public Collection<Student> getAllStudentsInDepartments(String departmentName, String lastNameLike) {

		Collection<Student> students = 
			studentDao.getAll().stream()
				.filter( p -> p.getDept().equals(departmentName))
				.filter( p -> p.getSurname().contains(lastNameLike))
				.toList();
		
		return students;
	} 

	@Override
	public void addStudent(Student student ){
		if ( student.getFirstName().isBlank() == false &&
		     student.getSurname().isBlank()   == false &&
			 student.getDept().isBlank()      == false  ) {
			studentDao.add(student);
		}
	}




}
