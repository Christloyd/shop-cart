package com.exercice.services;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exercice.http.Cart;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Verification service for carts. <br/>
 * This service class is responsible for verifying the data of the carts before processing them further.
 */
@Service("verificationService")
public class VerificationService extends AbstractService {

    // Regular expressions for validation
    private String chiffreRegex = "^(100000|[1-9][0-9]{0,4}|[0-9])$";
    private String nameRegex = "[a-zA-Z]([- ',.a-zA-Z]{0,48}[.a-zA-Z])?";
    private String quantityRegex = "^(?:[1-9]|[1-4][0-9]|50)$";

    /**
     * Remove accents from a given text. <br/>
     * This method uses Normalizer to remove diacritical marks (accents) from a given text.
     *
     * @param text the text to remove accents from
     * @return the text without accents
     */
    private String removeAccents(String text) {
        String copy = Normalizer.normalize(text, Normalizer.Form.NFD);
        copy = copy.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return copy;
    }

    /**
     * Verify the list of carts. <br/>
     * This method verifies the data of each cart in the provided list and calculates the total price of all carts.
     *
     * @param carts the list of carts to be verified
     * @return the total price of all carts if the verification is successful
     * @throws Exception if there are any validation errors, it throws an exception with error details in JSON format
     */
    public Optional<Double> Verification(List<Cart> carts) throws Exception {

        double total = 0;

        // HashMap to store validation errors
        HashMap<String, String> errors = new HashMap<>();

        // Iterate through each cart and perform validation
        for (Cart cart : carts) {
            // Validate the name of the cart
            String name = cart.getName();
            if (name == null || name.trim().isEmpty()) {
                errors.put("erName", "Name is required" + " FROM " + cart.getId());
            } else {
                name = cart.getName().trim();
                String withoutAccent = removeAccents(name);
                if (!withoutAccent.matches(nameRegex)) {
                    errors.put("erName", "Invalid name: " + cart.getName() + " FROM " + cart.getId());
                }
            }

            // Validate the price of the cart
            String price = Double.toString(cart.getPrice());
            if (price == null) {
                errors.put("erPrice", "Price is required" + " FROM " + cart.getId());
            } else {
                if (!price.matches(chiffreRegex) && cart.getPrice() < 0) {
                    errors.put("erPrice", "Invalid price: " + cart.getPrice() + " FROM " + cart.getId());
                }
            }

            // Validate the quantity of the cart
            String quantity = Double.toString(cart.getQuantity());
            if (quantity == null) {
                errors.put("erQuantity", "Quantity is required" + " FROM " + cart.getId());
            } else {
                if (!quantity.matches(quantityRegex) && cart.getQuantity() < 0) {
                    errors.put("erQuantity", "Invalid quantity: " + cart.getQuantity() + " FROM " + cart.getId());
                }
            }

            // Calculate the total price
            total += cart.getTotal();
        }

        // If there are any validation errors, throw an exception with the error details in JSON format
        if (!errors.isEmpty()) {
            ObjectMapper jsonMapper = new ObjectMapper();
            String errorsJson = jsonMapper.writeValueAsString(errors);
            throw new Exception(errorsJson);
        }

        // Return the total price if the verification is successful
        Optional<Double> option= Optional.of(total);
        return option;
    }

}
