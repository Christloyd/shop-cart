package com.exercice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exercice.entity.ProductEntity;
import com.exercice.http.Cart;
import com.exercice.services.VerificationService;

@SpringBootApplication
public class CartNatixisApplication {
	/** Main log. */
    private final static Log LOG = LogFactory.getLog(CartNatixisApplication.class);
    

	public static void main(String[] args) {
		SpringApplication.run(CartNatixisApplication.class, args);
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
			
			
			VerificationService service = (VerificationService) appContext
					.getBean("verificationService");
			
			
			Cart c1 = new Cart(cl1.getId(),cl1.getName(),cl1.getPrice());
			Cart c2 = new Cart(cl2.getId(),cl2.getName(),cl2.getPrice());
			Cart c3 = new Cart(cl3.getId(),cl3.getName(),cl3.getPrice());
			CartNatixisApplication.LOG.info(c1);
			CartNatixisApplication.LOG.info(c2);
			CartNatixisApplication.LOG.info(c3);
			
			
			List<Cart> carts = new ArrayList<>();
			carts.addAll(Arrays.asList(c1, c2, c3));
			
			double resultat = service.Verification(carts);
			CartNatixisApplication.LOG.info("le prix total est de : " + resultat + " euros.");
			
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

	}

}
