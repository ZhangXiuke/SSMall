package com.zxk.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAop {

	/*@Pointcut("execution(* com.zxk.service..*(..))")
	public void aspect(){}*/
	
	//@After("execution(* com.zxk.service..*(..))")
	/*@AfterReturning("aspect()")
	public void before(JoinPoint joinPoint){
		System.out.println("Hello AOP");
		 Object[] args = joinPoint.getArgs();
		 for(int i=0;i<args.length;i++){
			 System.out.println("????"+args[i]);
		 }
	}*/
}
