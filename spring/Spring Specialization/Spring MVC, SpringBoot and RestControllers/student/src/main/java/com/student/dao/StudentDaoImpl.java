package com.student.dao;
 
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.student.core.Student;

/**
 * Handle database operations for the Student Instance
 */
public class StudentDaoImpl implements StudentDao {

	private Map<Long, Student> students;
	{
		students = new HashMap<>();
		students.put(1L, new Student("Eric", "Colbert", "English Literature", 145.00));
		students.put(2L, new Student("Mary", "Contrary", "Physics", 155.00));
		students.put(3L, new Student("Jason", "Stewart", "English Literature", 145.00));
		
	}
	@Override
	public Student getOne(long id) {
		return students.get(id);
	}
	@Override
	public Collection<Student> getAll() {
		return students.values();
	}

	@Override
	public void add(Student student ) {
		long id = students.keySet().stream().count();
		id++;
		student.setId(id);
		students.put(id, student);

	}

}
