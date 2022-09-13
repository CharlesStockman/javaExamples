package com.student.microservice.service;

import java.util.Collection;

import com.student.microservice.core.Student;

@EnableDiso
public interface StudentService {
	
	Student get(long id);

	/**
	 * Return all the students going to the college
	 * 
	 * @return A list of all the students going to the colllege
	 */
	Collection<Student> getAllStudents();

	/**
	 * Returns a list of students for a speicified department
	 * 
	 * @param department     The department to get the students from 
	 * 
	 * @return A list of student from the department which can have zero students
	 */
	public Collection<Student> getStudentsByDepartment(String department);

	/**
	 * Returns a list of students that paid a specific fee
	 * 
	 * @param fee 		The fee that was paid for the class
	 * @return A list of students who paid the fee.
	 */
	public  Collection<Student> getStudentsWhoPaidExactFee(double fee);

	/** 
	 * Performs the instertion of a Student
	 * 
	 * @param student 		The Student's informatin to be added to the system
	 */
	public void insertStudent(Student student);

}
