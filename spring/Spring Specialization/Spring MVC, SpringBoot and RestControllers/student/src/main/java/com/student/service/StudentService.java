package com.student.service;

import java.util.Collection;

import com.student.core.Student;

public interface StudentService {
	
	Student get(long id);
	Collection<Student> getAllStudents();
	
	/**
	 * Returns the students instances starting at the front of the list
	 * 
	 * @param firstNStudents -- The number of students to be returned
	 * @return A sublist of student where the firstNStudents items are returned.
	 */
	Collection<Student> getFirstNStudents(Integer firstNStudents);

	/**
	 * Get the Student by the department they are currently in.
	 * 
	 * @param Department 		The name of department the students are in
	 * @return A collection of student from the department
	 */
	Collection<Student> getStudentsFromDeparment();

	/**
	 * Get the student by the department and a portion of their last name
	 * 
	 * @param String departmentName 	Where the student is doing their major
	 * @param Stirng lastNameLike		Where the last name	
	 * 
	 * @return The students that belong to specific department and their name returns a String
	 */
	public Collection<Student> getAllStudentsInDepartments(String departmentName, String lastNameLike );

	/**
	 * Add a student to database by validating the data and then passing the data to the StudentDao
	 * 
	 * @param student The student instnace being inserted into the database
	 */
	public void addStudent(Student student );
}
