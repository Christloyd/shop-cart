/**
 * Main application class for the Cart Natixis application.
 */
package com.exercice;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exercice.entity.ProductEntity;
import com.exercice.http.Cart;
import com.exercice.services.VerificationService;

/**
 * Main class that serves as the entry point for the Cart Natixis application.
 */
@SpringBootApplication
public class CartNatixisApplication {
    /** Main log for the application. */
    private final static Log LOG = LogFactory.getLog(CartNatixisApplication.class);

    /**
     * Main method to start the Cart Natixis application.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CartNatixisApplication.class, args);
        if (CartNatixisApplication.LOG.isInfoEnabled()) {
            CartNatixisApplication.LOG.info("-- Start -- ");
        }

     // Create a new ClassPathXmlApplicationContext to load the Spring context configuration file "data-context.xml".
        ClassPathXmlApplicationContext appContext = null;
        try {
            appContext = new ClassPathXmlApplicationContext("data-context.xml");

            // Get the VerificationService bean from the application context.
            VerificationService service = (VerificationService) appContext.getBean("verificationService");

            // Get three ProductEntity beans (c1, c2, and c3) from the application context.
            ProductEntity cl1 = (ProductEntity) appContext.getBean("c1");
            ProductEntity cl2 = (ProductEntity) appContext.getBean("c2");
            ProductEntity cl3 = (ProductEntity) appContext.getBean("c3");

            // Log information about the retrieved ProductEntity objects.
            CartNatixisApplication.LOG.info(cl1);
            CartNatixisApplication.LOG.info(cl2);
            CartNatixisApplication.LOG.info(cl3);

            // Create three Cart objects (c1, c2, and c3) using the data from the ProductEntity objects.
            Cart c1 = new Cart(cl1.getId(), cl1.getName(), cl1.getPrice());
            Cart c2 = new Cart(cl2.getId(), cl2.getName(), cl2.getPrice());
            Cart c3 = new Cart(cl3.getId(), cl3.getName(), cl3.getPrice());

            // Log information about the created Cart objects.
            CartNatixisApplication.LOG.info(c1);
            CartNatixisApplication.LOG.info(c2);
            CartNatixisApplication.LOG.info(c3);
            CartNatixisApplication.LOG.info(c3);

            // Create the list of carts
            List<Cart> carts = List.of(c1, c2, c3);

            // Filter duplicates based on cart ID
            List<Cart> distinctCarts = carts.stream()
                    .collect(Collectors.toMap(Cart::getId, cart -> cart, (existing, replacement) -> existing))
                    .values()
                    .stream()
                    .collect(Collectors.toList());

            // Calculate the total price by calling the Verification method of the service and passing the distinctCarts list.
            double resultat = service.Verification(distinctCarts);
            
            // Log the total price in euros using the application's logger.
            CartNatixisApplication.LOG.info("Total price is: " + resultat + " euros.");

        } catch (Exception e) {
            CartNatixisApplication.LOG.fatal("Error", e);
            System.exit(-1);
        } finally {
            if (appContext != null) {
                appContext.close();
            }
        }
        if (CartNatixisApplication.LOG.isInfoEnabled()) {
            CartNatixisApplication.LOG.info("-- End -- ");
        }
    }

}
