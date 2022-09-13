package com.student.microservice.aspect;

import javax.inject.Named;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.annotations.common.util.impl.LoggerFactory;

import com.student.microservice.core.Student;

/**
 * The purpose is to put all the logging into one place.
 */
@Named
@Aspect
public class StudentAspect {
   private org.jboss.logging.Logger LOG = LoggerFactory.logger(this.getClass());

   /**
    * Query to find where the code will be executed.
    */
   @Pointcut("execution( * com.student.service.*.*(..))")
   public void log() {
    
   }

   /**
    * Code that will be executed before the point cut.
    *
    * @param joinPoint    Information about the point where the code will be executed
    */
   @Before("log()")
   public void before(JoinPoint joinPoint) {
      LOG.info("Invoked Method Before -> " + joinPoint.getSignature().getName());
   }

   /**
    * Code that will be executed after the point cut.
    *
    * @param joinPoint    Information about the point where the code will be executed
    */
   @After("log()")
   public void after(JoinPoint joinPoint) {
      LOG.info("Invoked Method after -> " + joinPoint);
   } 

   /**
    * Code that will be execute before and after the point cut
    *
    * @param joinPoint      -- Has a function proceed ( changes state from before to after)
    * @param id             -- The primary key of a student in a database.
    *
    * @return The student the primary key from parameter id
    * 
    * @throws Throwable -- If the proceed throws anything
    */
   @Around("log() && args(id)")
   public Object around(ProceedingJoinPoint joinPoint, long id) throws Throwable {
      LOG.info("Around before->" + joinPoint.getSignature().getName() + " with id " + id);
      Student student = (Student)joinPoint.proceed();
      LOG.info("Around after->" + student.getFirstName() + " " + student.getSurname());
      return student;
   }

}
