package com.student.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.student.core.Student;
import com.student.dao.StudentDao;

public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;

	private Optional<Integer> numberOfStudents = null;

	/**
	 * Retrieve the number of students.
	 * 
	 * @return The value or the default ( Integer.MAX_VALUE ) if the value is not present.
	 */
	public Integer getNumberOfStudents(){ 
		return numberOfStudents.orElse(Integer.MAX_VALUE);
	}

	/**
	 * Create a optional with the numberOfStudents from the beans.xml
	 * 
	 * @param value    The value found in the beans.xml
	 */
	public void setNumberOfStudents(int value ) {
		numberOfStudents = Optional.of(value);
	}

	public void setDaoForService(StudentDao dao ) {
		this.studentDao = dao;
	}

	@Override
	public Student get(long id) {
		return studentDao.getOne(id);
	}

	@Override
	public Collection<Student> getAllStudents() {
		return studentDao.getAll();
	}

	@Override
	public Collection<Student> getFirstNStudents() {
		List<Student> students = new ArrayList<Student>(studentDao.getAll()).stream().limit(getNumberOfStudents()).toList();
		return (Collection<Student>)students;
	}

}
