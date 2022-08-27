package com.student.aspect;

import javax.inject.Named;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.annotations.common.util.impl.LoggerFactory;

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

}
