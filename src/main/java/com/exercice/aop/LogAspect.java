package com.exercice.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Log tous les appels vers les services.
 */
@Component
@Aspect
public class LogAspect {
	private final static Log LOG = LogFactory.getLog(LogAspect.class);

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
}