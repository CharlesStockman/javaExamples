package com.student.microservice.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.student.microservice.core.Student;
import com.student.microservice.exception.MyException;

/**
 * Create a repository for  
 */
@RepositoryRestResource(collectionResourceRel="student", path="enrollments", excerptProjection = NameProjection.class)
public interface StudentRepository extends JpaRepository<Student, Long>{

    /**
     * Find Students by fee paid for the class
     * 
     * @param   fee         The amount paid for the class
     * @return A list who paid the exact fee
     */
    public Collection<Student> findByFees(double fee);

    @Query("Select student from Student student where student.dept = :dept")
    Collection<Student> getByDept(@Param("dept") String department);

    // Creates a new transaction which will save the data and the transaction 
    // in the controller will not be used since we are creating a new one.
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Student save(Student entity);

    
}