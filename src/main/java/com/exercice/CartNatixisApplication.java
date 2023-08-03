package com.exercice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exercice.aop.BeanConfig;
import com.exercice.entity.ProductEntity;
import com.exercice.services.VerificationService;


public class CartNatixisApplication {
	/** Main log. */
    private final static Log LOG = LogFactory.getLog(CartNatixisApplication.class);

	public static void main(String[] args) {
		if (CartNatixisApplication.LOG.isInfoEnabled()) {
    		CartNatixisApplication.LOG.info("-- Debut -- ");
        }
		ClassPathXmlApplicationContext appContext = null;
		try {
			appContext = new ClassPathXmlApplicationContext("data-context.xml");
			
			ProductEntity cl1 = (ProductEntity) appContext.getBean("c1");
			ProductEntity cl2 = (ProductEntity) appContext.getBean("c2");
			ProductEntity cl3 = (ProductEntity) appContext.getBean("c3");

			
			CartNatixisApplication.LOG.info(cl1);
			CartNatixisApplication.LOG.info(cl2);
			CartNatixisApplication.LOG.info(cl3);
			
			
			
			
			
		} catch (Exception e) {
			CartNatixisApplication.LOG.fatal("Erreur", e);
			System.exit(-1);
		} finally {
			if (appContext != null) {
				appContext.close();
			}
		}
    	if (CartNatixisApplication.LOG.isInfoEnabled()) {
    		CartNatixisApplication.LOG.info("-- Fin -- ");
        }
        System.exit(0);
    	
	}

}
