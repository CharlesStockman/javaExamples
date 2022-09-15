package com.student.microservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.microservice.core.Course;

/**
 * Create a repository that maps Student Object to a Student Table
 */
public interface CoursesRepository extends JpaRepository<Course, Long> {
    
}
