package com.exercice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exercice.entity.ProductEntity;
import com.exercice.http.Cart;
import com.exercice.services.VerificationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class JsonMain {
	/** Main log. */
    private final static Log LOG = LogFactory.getLog(JsonMain.class);
    
	public static void main(String[] args) {
		SpringApplication.run(JsonMain.class, args);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		if (JsonMain.LOG.isInfoEnabled()) {
    		JsonMain.LOG.info("-- Debut -- ");
        }
		ClassPathXmlApplicationContext appContext = null;
		try {
			appContext = new ClassPathXmlApplicationContext("data-context.xml");
			File file = new File("src/main/resources/product.json");
			
			VerificationService service = (VerificationService) appContext
					.getBean("verificationService");
			
			List<ProductEntity> products = objectMapper.readValue(file, new TypeReference<List<ProductEntity>>() {});
			
			List<Cart> carts = new ArrayList<>();
			for (ProductEntity product : products) {
                Cart c1 = new Cart(product.getId(), product.getName(), product.getPrice());
                carts.add(c1); // Ajoutez le panier à la liste
			}

			double resultat = service.Verification(carts);
			JsonMain.LOG.info("le prix total est de : " + resultat + " euros.");
			
		} catch (Exception e) {
			JsonMain.LOG.fatal("Erreur", e);
			System.exit(-1);
		} finally {
			if (appContext != null) {
				appContext.close();
			}
		}
    	if (JsonMain.LOG.isInfoEnabled()) {
    		JsonMain.LOG.info("-- Fin -- ");
        }

	}
}
