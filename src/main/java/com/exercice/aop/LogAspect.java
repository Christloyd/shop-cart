/**
 * This class serves as an Aspect for logging all calls to services.
 */
package com.exercice.aop;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.exercice.http.Cart;

/**
 * This Aspect logs all calls to services.
 */
@Component
@Aspect
public class LogAspect {
	private final static Log LOG = LogFactory.getLog(LogAspect.class);

	private BufferedWriter bufferedWriter;

	/**
	 * Constructor for the LogAspect object.
	 */
	public LogAspect() {
		super();
	}

	/**
	 * Executed before calling a service.
	 *
	 * @param jp the join point
	 */
	@Before("execution(* com.exercice.services.AbstractService+.*(..) )")
	public void logBefore(JoinPoint jp) {
	    if (LogAspect.LOG.isInfoEnabled()) {
			LogAspect.LOG.info("Before calling " + jp.getTarget() + " " + jp.getSignature());
		}
	}

	/**
	 * Executed after calling a service.
	 *
	 * @param jp the join point
	 */
	@After("execution(* com.exercice.services.AbstractService+.*(..) )")
	public void logAfter(JoinPoint jp) {
	    if (LogAspect.LOG.isInfoEnabled()) {
			LogAspect.LOG.info("After calling " + jp.getTarget() + " " + jp.getSignature());
		}
	}

	/**
	 * Initializes the log file for cart data before calling a service.
	 *
	 * @throws IOException if an I/O error occurs
	 */
	public void beforeCart() throws IOException {
	    FileWriter writer = new FileWriter("C:/temp/Main.json");
	    bufferedWriter = new BufferedWriter(writer);
	    bufferedWriter.write(String.format("%-20s %-20s %-20s %-20s%n", "Product", "Quantity", "Price", "Total"));
	    bufferedWriter.write("---------------------------------------------------------------------");
	    bufferedWriter.newLine();
	}

	/**
	 * Logs the cart data after calling a service with the returned value.
	 *
	 * @param retVal the returned value from the service
	 * @throws IOException if an I/O error occurs
	 */
	public void afterCart(double retVal) throws IOException {
	    bufferedWriter.write("---------------------------------------------------------------------");
	    bufferedWriter.newLine();
	    bufferedWriter.write(String.format("%-20s %-20s %-20s %-20s%n", "Total", "", "", retVal));
	    bufferedWriter.close();
	}

	/**
	 * Logs the cart data between before and after calling a service.
	 *
	 * @param jp the join point
	 * @throws IOException if an I/O error occurs
	 */
	public void betweenCart(JoinPoint jp) throws IOException {
		Object[] args = jp.getArgs();

		@SuppressWarnings("unchecked")
		List<Cart> carts = (List<Cart>) args[0];

		for (Cart cart : carts) {
		    bufferedWriter.write(String.format("%-20s %-20s %-20s %-20s%n", cart.getName(), cart.getQuantity(), cart.getPrice(), cart.getTotal()));
		}
	}

	/**
	 * Pointcut definition for the afterReturning advice.
	 */
	@Pointcut("execution(* com.exercice.services.AbstractService+.*(..) )")
	public void afterReturningPointCut() {}

	/**
	 * Executed after returning from a service call and logs cart data.
	 *
	 * @param jp the join point
	 * @param retVal the returned value from the service
	 * @throws IOException if an I/O error occurs
	 */
	@AfterReturning(pointcut = "afterReturningPointCut()", returning = "retVal")
	public void afterReturning(JoinPoint jp, double retVal) throws IOException {
		beforeCart();
		betweenCart(jp);
		afterCart(retVal);
	}
}