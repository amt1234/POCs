package com.microservice.patient.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AspectConfig {

	private Logger logger = LoggerFactory.getLogger(AspectConfig.class);
	
	@Before(value = "execution(* com.microservice.patient.controller.PatientController.*(..))")
	public void logStatementBefore(JoinPoint joinPoint) {
		logger.info("Executing {}",joinPoint);
	}
	
	@After(value="execution(* com.microservice.patient.controller.PatientController.*(..))")
	public void logStatementAfter(JoinPoint joinPoint) {
		logger.info("Complete execution of {}",joinPoint);
	}
}
