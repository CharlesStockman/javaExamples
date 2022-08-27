package com.student.aspect;

import javax.inject.Named;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;

@Named
@Aspect
public class StudentAspect {
   private org.jboss.logging.Logger LOG = LoggerFactory.logger(this.getClass());

   @Pointcut("execution( * com.student.service.*.*(..))")
   public void log() {
    
   }

   @Before("log()")
   public void before(JoinPoint joinPont) {
      LOG.info("Invoked Method Before -> " + joinPont.getSignature().getName());
   }

   @After("log()")
   public void after(JoinPoint joinPoint) {
      LOG.info("Invoked Method after -> " + joinPoint);
   } 

}
