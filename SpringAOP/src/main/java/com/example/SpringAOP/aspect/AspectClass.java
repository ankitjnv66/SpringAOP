package com.example.SpringAOP.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Configuration   // or we can use @Component to get spring know about this
public class AspectClass {

	
	/*	1.
	 *  Advice will apply for all methods of all classes of model package
		@Before("execution(* com.example.SpringAOP.model.*.*())")
		public void loggingAdvice() {
			System.out.println("calling before aspect");
		}
	*/
	
	
	
	
	/*	2.
	 *  Advice will apply for getName() method of circle class
	 * 
		@Before("execution(public String com.example.SpringAOP.model.Circle.getName())")
		public void loggingAdvice() {
			System.out.println("calling before aspect");
		}
	*/
	
	
	
	/*	3.
	 * 	get*()  for nay method start with get and without argument
	 * 	get*(..)  for nay method start with zero or more argument
	 * 	get*(*)  for nay method start with one or more  argument	 
	 * */
	
	// we can replace these two methods using pointcut
	/*
	@Before("execution(public * com.example.SpringAOP.model.*.get*(..))")
	public void loggingAdvice() {
		System.out.println("calling before aspect");
	}
	
	@Before("execution(public * com.example.SpringAOP.model.*.get*(..))")
	public void secondAdvice() {
		System.out.println("second advice executed");
	}	
	*/
	
	/*
	@Before("pointCutForAllGetters()")
	public void loggingAdvice() {
		System.out.println("calling before aspect");
	}
	*/
	
	/*combining advices 
	
	@Before("pointCutForAllGetters() && getAllCircleMethods()")
	public void loggingAdvice() {
		System.out.println("calling before aspect");
	}
	*/
	
	
	//Using JOIN POINTS
	@Before("getAllCircleMethods()")
	public void loggingAdvice(JoinPoint joinPoint) {
		System.out.println(joinPoint.toString()+" :: calling before aspect");
		//System.out.println(joinPoint.getTarget());
	}
	
	@Before("args(name)")
	public void stringArgMethod(String name) {
		System.out.println("this is called for method having one argument");
	}
	
	/*
	@Before("pointCutForAllGetters()")
	public void secondAdvice() {
		System.out.println("second advice executed..");
	}	
	*/
	
	// PointCuts to use
	
	@Pointcut("execution(public * com.example.SpringAOP.model.*.get*())")
	public void pointCutForAllGetters() {
		
	}
	
	
	/*
	 * within() it take class names as input ie. within("com.example.SpringAOP.model.Circle")
	 * execution() takes methods as input ie. execution(public * com.example.SpringAOP.model.*.get*(..))
	 * */ 
	
	/* IMPORTANE NOTE
	 * OR if we want to apply pointcut for all the methods of all the classes inside particular package
	 * @Pointcut("within(com.example.SpringAOP.model.*)")
	 * */
	
	/* IMPORTANE NOTE
	 * 
	 * OR if we want to apply pointcut for all the methods of all the classes inside particular package and subpackeges as well
	 * @Pointcut("within(com.example.SpringAOP.model..*)")
	 * */
	
	//@Pointcut("execution(* com.example.SpringAOP.model.Circle.*(..))") we can do same using another way ie. within
	@Pointcut("within(com.example.SpringAOP.model.Circle)")
	public void getAllCircleMethods() {		
	}
	
	/*
	 * 	3. @Pointcut(args())
	 * 		args take classes or interface as an argument
	 * */
	
	
	/*
	 * COMBINING POINTCUTS
	 * @Before("allGetters() && allMethodsOfCircle()")
	 * public void applyAdvice(){
	 * 		//codes
	 * }
	 * */
	
	
}
