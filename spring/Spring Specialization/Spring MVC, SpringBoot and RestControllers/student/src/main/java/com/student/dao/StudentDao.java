package com.student.dao;

import java.util.Collection;

import com.student.core.Student;

public interface StudentDao {
	
	Student getOne(long id);
	Collection<Student> getAll();

	/**
	 * Adds a student instance to the Database
	 * 
	 * @param student	The student to be added to the database
	 * 
	 */
	public void add(Student student);
}
