package com.student.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.student.core.Student;

/**
 * Create a repository for  
 */
@RepositoryRestResource(collectionResourceRel="student", path="enrollments", excerptProjection = NameProjection.class)
public interface StudentRepository extends JpaRepository<Student, Long>{

    /**
     * Find Studens by fee paid for the class
     * 
     * @param   fee         The amount paid for the class
     * @return A list who paid the exact fee
     */
    public Collection<Student> findByFees(double fee);

    @Query("Select student from Student student where student.dept = :dept")
    Collection<Student> getByDept(@Param("dept") String department);
    
}