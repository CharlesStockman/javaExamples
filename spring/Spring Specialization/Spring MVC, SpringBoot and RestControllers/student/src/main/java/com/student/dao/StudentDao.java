package com.student.dao;

import java.util.Collection;

import com.student.core.Student;

/**
 * An interface for DataBase instance that perform CRUD on Student
 */
public interface StudentDao {

	/**
	 * Get one student based on id
	 * @param   id 	The primary key of the student
	 * @return  The Student's information
	 */
	Student getOne(long id);

	/**
	 * Get all the student's
	 * @return
	 */
	Collection<Student> getAll();

	/**
	 * Adds a student instance to the Database
	 * 
	 * @param student	The student to be added to the database
	 * 
	 */
	public void add(Student student);
}
