package com.nikunj.springboot;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectMethods {

	public static final Logger logger = LoggerFactory.getLogger(AspectMethods.class);

	@Before("@annotation(Validator)")
	public void validateRequest(JoinPoint joinPoint) {
		System.out.println("@Before");
		System.out.println("Agruments Passed=" + Arrays.toString(joinPoint.getArgs()));

	}

	@Around("@annotation(Validator)")
	public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		System.out.println("Before invoking getUser() method");
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("After invoking getUser() method. Return value=" + value);
		return value;
	}

	@After("@annotation(Validator)")
	public void afterValidation(JoinPoint joinPoint) {
		System.out.println("@After");

	}
}
