package com.exercice.controllers;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercice.serialize.Serialization;
import com.exercice.entity.ProductEntity;
import com.exercice.http.Cart;
import com.exercice.services.VerificationService;

@RestController
@RequestMapping("/cart")
public class CartController extends AbstractController {
    @Autowired
    VerificationService verificationService;

    private static final long serialVersionUID = 1L;

    public CartController() {
        super();
    }

    @SuppressWarnings("unchecked")
    @GetMapping("")
    public void createJsonCart() {
        // Initialize a list to store the deserialized ProductEntity objects.
        List<ProductEntity> products = null;

        try {
            // Serialize product data and store it in a file named "product.ser".
            Serialization.serializeProduct();

            // Read the serialized product data from the "product.ser" file using ObjectInputStream.
            FileInputStream fileIn = new FileInputStream("product.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // Read the object from the file and check if it is an instance of List<ProductEntity>.
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                products = (List<ProductEntity>) obj;
            } else {
                // If the deserialized object is not a list of ProductEntity, throw an exception.
                in.close();
                fileIn.close();
                throw new IllegalArgumentException("The deserialized object is not a list of ProductEntity.");
            }

            // Close the ObjectInputStream and FileInputStream after reading the data.
            in.close();
            fileIn.close();
            
            this.LOG.info("Deserialized Object: " + products);

            // Initialize a list to store the created Cart objects.
            List<Cart> carts = new ArrayList<>();

            // Initialize a set to keep track of unique product IDs.
            Set<Integer> uniqueIds = new HashSet<>();

            // Loop through the deserialized ProductEntity objects and create Cart objects.
            for (ProductEntity product : products) {
                int productId = product.getId();
                // Check if the product ID is unique.
                if (!uniqueIds.contains(productId)) {
                    // If the product ID is unique, create a new Cart object and add it to the list.
                    Cart cart = new Cart(productId, product.getName(), product.getPrice());
                    carts.add(cart);
                    // Add the product ID to the set to track uniqueness.
                    uniqueIds.add(productId);
                }
            }

            // Calculate the total price of the carts using the VerificationService.
            double resultat = verificationService.Verification(carts);

            // Log the total price using the application's logger.
            this.LOG.info("The total price from the Controller is: " + resultat + " euros.");

        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and displaying an error message.
            e.printStackTrace();
            System.err.println("An error occurred while writing the file: " + e.getMessage());
        }

    }

}
