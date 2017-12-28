package com.java.ms.template.book.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLoggingAspect {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceLoggingAspect.class);

	@Before("execution(* com.java.ms.template.book.service.*.*(..))")
	public void beforeFindByAccountIdCreation(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		LOG.debug("Before ["+name+"] executed");
	}
	
	@After("execution(* com.java.ms.template.book.service.*.*(..))")
	public void afterFindByAccountIdCreation(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		LOG.debug("After ["+name+"] executed");
	}
	
	@AfterReturning(pointcut = "execution(* com.java.ms.template.book.service.*.*(..))",
					returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		String name = joinPoint.getSignature().getName();
		LOG.debug("AfterReturning ["+name+"] executed");
		LOG.debug("Result from ["+name+"]: "+result.toString());
	}
}
