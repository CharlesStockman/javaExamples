package com.student.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.core.Student;

/**
 * Create a repository for Student
 */
public interface StudentRepository extends JpaRepository<Student, Long>{

    /**
     * Find Studens by fee paid for the class
     * 
     * @param   fee         The amount paid for the class
     * @return A list who paid the exact fee
     */
    public Collection<Student> findByFees(double fee);
    
}