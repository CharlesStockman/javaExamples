package com.student.service;

import java.util.Collection;

import com.student.core.Student;

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

}