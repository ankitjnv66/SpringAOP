package com.example.SpringAOP.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration // or we can use @Component to get spring know about this
public class AspectClass {

	/*
	 * 1. Advice will apply for all methods of all classes of model package
	 * 
	 * @Before("execution(* com.example.SpringAOP.model.*.*())") public void
	 * loggingAdvice() { System.out.println("calling before aspect"); }
	 */

	/*
	 * 2. Advice will apply for getName() method of circle class
	 * 
	 * @Before("execution(public String com.example.SpringAOP.model.Circle.getName())"
	 * ) public void loggingAdvice() { System.out.println("calling before aspect");
	 * }
	 */

	/*
	 * 3. get*() for nay method start with get and without argument get*(..) for nay
	 * method start with zero or more argument get*(*) for nay method start with one
	 * or more argument
	 */

	// we can replace these two methods using pointcut
	/*
	 * @Before("execution(public * com.example.SpringAOP.model.*.get*(..))") public
	 * void loggingAdvice() { System.out.println("calling before aspect"); }
	 * 
	 * @Before("execution(public * com.example.SpringAOP.model.*.get*(..))") public
	 * void secondAdvice() { System.out.println("second advice executed"); }
	 */

	/*
	 * @Before("pointCutForAllGetters()") public void loggingAdvice() {
	 * System.out.println("calling before aspect"); }
	 */

	/*
	 * combining advices
	 * 
	 * @Before("pointCutForAllGetters() && getAllCircleMethods()") public void
	 * loggingAdvice() { System.out.println("calling before aspect"); }
	 */

	// Using JOIN POINTS
	/*
	 * @Before("getAllCircleMethods()") public void loggingAdvice(JoinPoint
	 * joinPoint) {
	 * System.out.println(joinPoint.toString()+" :: calling before aspect");
	 * //System.out.println(joinPoint.getTarget()); }
	 */

	/*
	 * @Before("args(name)") public void stringArgMethod(String name) {
	 * System.out.println("this is called for method having one argument"); }
	 */
	/*
	 * @Before("pointCutForAllGetters()") public void secondAdvice() {
	 * System.out.println("second advice executed.."); }
	 */

	// PointCuts to use

	@Pointcut("execution(public * com.example.SpringAOP.model.*.get*())")
	public void pointCutForAllGetters() {

	}

	/*
	 * within() it take class names as input ie.
	 * within("com.example.SpringAOP.model.Circle") execution() takes methods as
	 * input ie. execution(public * com.example.SpringAOP.model.*.get*(..))
	 */

	/*
	 * IMPORTANE NOTE OR if we want to apply pointcut for all the methods of all the
	 * classes inside particular package
	 * 
	 * @Pointcut("within(com.example.SpringAOP.model.*)")
	 */

	/*
	 * IMPORTANE NOTE
	 * 
	 * OR if we want to apply pointcut for all the methods of all the classes inside
	 * particular package and subpackeges as well
	 * 
	 * @Pointcut("within(com.example.SpringAOP.model..*)")
	 */

	// @Pointcut("execution(* com.example.SpringAOP.model.Circle.*(..))") we can do
	// same using another way ie. within
	@Pointcut("within(com.example.SpringAOP.model.Circle)")
	public void getAllCircleMethods() {
	}

	/*
	 * 3. @Pointcut(args()) args take classes or interface as an argument
	 */

	/*
	 * COMBINING POINTCUTS
	 * 
	 * @Before("allGetters() && allMethodsOfCircle()") public void applyAdvice(){
	 * //codes }
	 */

	// @After : it will execute after the given method runs no metter method got
	// successfully completed or not
	/*
	 * @After("getAllCircleMethods()") public void loggingAdvice(JoinPoint
	 * joinPoint) {
	 * System.out.println(joinPoint.toString()+" :: calling after aspect");
	 * //System.out.println(joinPoint.getTarget()); }
	 */

	/*
	 * //To execute some advice to run after successful completion of a particular
	 * method we'll use @AfterReturning if method not executed successfully it won't
	 * run
	 * 
	 * @AfterReturning("getAllCircleMethods()") public void loggingAdvice(JoinPoint
	 * joinPoint) {
	 * System.out.println(joinPoint.toString()+" :: calling after aspect");
	 * //System.out.println(joinPoint.getTarget()); }
	 */

	@AfterThrowing("getAllCircleMethods()")
	public void adviceToRunAfterException() {
		System.out.println(" :: calling after throwing aspect");
		// System.out.println(joinPoint.getTarget());
	}

	@Pointcut("execution(* com.example.SpringAOP.model.Circle.return*(..))")
	public void getReturnedObjMethod() {

	}

	@AfterReturning(pointcut = "getReturnedObjMethod()", returning = "returnedValue")
	public void adviceToGetReturnedValue(String returnedValue) {
		System.out.println("returned value is :: " + returnedValue);
	}

	@Pointcut("execution(* com.example.SpringAOP.model.Triangle.check*())")
	public void getReturnedException() {
	}

	@AfterThrowing(pointcut = "getReturnedException()", throwing = "ex")
	public void adviceafterThrowingEx(Exception ex) {
		System.out.println("thrown exception  is :: " + ex.toString());
	}

	// Use of @Around advice
	@Around("execution(* com.example.SpringAOP.model.Triangle.around*())")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		
		Object obj = null;
		
		try {
			System.out.println("Before proceed join point");
			obj = proceedingJoinPoint.proceed();
			System.out.println("After proceed join point");
		}catch(Throwable ex) {
			System.out.println("caught it !!");
			ex.printStackTrace();
		}
		
		System.out.println("finally proceed join point with returned value as :: "+obj);
		return obj;
	}
	
	
	//Custom annotation to make it easy to apply on multiple methods across the project to avoid problem to choose exact pointcut
	
	@Before("@annotation(com.example.SpringAOP.aspect.CustomAnnotation)")
	public void customAnoAdvice() {
		System.out.println("Custom annotation is being used..!!");
	}
	
	

}
