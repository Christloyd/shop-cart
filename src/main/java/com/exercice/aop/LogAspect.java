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
 * Log tous les appels vers les services.
 */
@Component
@Aspect
public class LogAspect {
	private final static Log LOG = LogFactory.getLog(LogAspect.class);

	
	private BufferedWriter bufferedWriter;
	/**
	 * Constructeur de l'objet.
	 */
	public LogAspect() {
		super();
	}

	/**
	 * Executer avant l'appel à un service.
	 * 
	 * @param jp
	 *          le join point
	 */
	@Before("execution(* com.exercice.services.AbstractService+.*(..) )")
	public void logBefore(JoinPoint jp) {
	    if (LogAspect.LOG.isInfoEnabled()) {
			LogAspect.LOG.info("Passage avant " + jp.getTarget() + " " + jp.getSignature());
		}
	}

	/**
	 * Executer après l'appel à un service.
	 * 
	 * @param jp
	 *          le join point
	 */
	@After("execution(* com.exercice.services.AbstractService+.*(..) )")
	public void logAfter(JoinPoint jp) {
	    if (LogAspect.LOG.isInfoEnabled()) {
			LogAspect.LOG.info("Passage apres " + jp.getTarget() + " " + jp.getSignature());
		}
	}
	
	public void beforeCart() throws IOException {
	    FileWriter writer = new FileWriter("src/main/resources/fichierMain.txt");
	    bufferedWriter = new BufferedWriter(writer);
	    bufferedWriter.write(String.format("%-20s %-20s %-20s %-20s%n", "Product", "Quantity", "Price", "Total"));
	    bufferedWriter.write("---------------------------------------------------------------------");
	    bufferedWriter.newLine();
	}
	
	public void afterCart(double retVal) throws IOException {
	    bufferedWriter.write("---------------------------------------------------------------------");
	    bufferedWriter.newLine();
	    bufferedWriter.write(String.format("%-20s %-20s %-20s %-20s%n", "Total", "", "", retVal));
	    bufferedWriter.close();
	}
	
	
	public void betweenCart(JoinPoint jp) throws IOException {
		

		Object[] args = jp.getArgs();

		@SuppressWarnings("unchecked")
		List<Cart> carts = (List<Cart>) args[0];

		for (Cart cart : carts) {
		    bufferedWriter.write(String.format("%-20s %-20s %-20s %-20s%n", cart.getName(), cart.getQuantity(), cart.getPrice(), cart.getTotal()));
		}

	}
	
	
	@Pointcut("execution(* com.exercice.services.AbstractService+.*(..) )")
	public void afterReturningPointCut() {}
	
	@AfterReturning(pointcut = "afterReturningPointCut()", returning = "retVal")
	public void afterReturning(JoinPoint jp, double retVal) throws IOException {
		

		beforeCart();
		betweenCart(jp);
		afterCart(retVal);

	}
}