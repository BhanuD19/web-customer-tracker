package com.vaibsD.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	//setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	//setup pointcut declaration
	@Pointcut("execution(* com.vaibsD.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.vaibsD.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.vaibsD.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	
	//add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		String method = theJoinPoint.getSignature().getName();
		myLogger.info("====>>> in before advice: calling method: " + method);
		
		Object[] args = theJoinPoint.getArgs();
		
		for (Object tempArgs : args) {
			myLogger.info("====> argument: " + tempArgs);
		}
	}
	
	@AfterReturning(pointcut="forAppFlow()",
					returning="theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		String method = theJoinPoint.getSignature().getName();
		myLogger.info("====>>> in afterReturning advice: calling method: " + method);
		
		myLogger.info("===>>> result: " + theResult);
		
	}
	
}
