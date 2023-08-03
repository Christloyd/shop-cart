package com.exercice.services;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.exercice.http.Cart;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("verificationService")
public class VerificationService extends AbstractService{
	
	// 1 à 100 000
	private String chiffreRegex = "^(100000|[1-9][0-9]{0,4}|[0-9])$";
	// 0 à 50
	private String nameRegex = "[a-zA-Z]([- ',.a-zA-Z]{0,48}[.a-zA-Z])?";
	// 1 à 50 inclusif
	private String quantityRegex = "^(?:[1-9]|[1-4][0-9]|50)$";
	
	private String supprimerAccents(String texte){
        String copie = 
            Normalizer.normalize(texte, Normalizer.Form.NFD);
        copie = 
           copie.replaceAll("[\\p{InCombiningDiacriticalMarks}]", ""); // le "" enleve les accents vu que cest une chaine vide
        return copie;
    }
	
	public double Verification(List<Cart> carts) throws Exception {
		
		double total = 0;

		
		HashMap<String, String> erreurs = new HashMap<>();
		
		for (Cart cart : carts) {
			
			String name = cart.getName();
			if(name == null || name.trim().isEmpty()){
	            erreurs.put("erName", "Name obligatoire" + " FROM " + cart.getId());
			} else {
				name = cart.getName().trim();
	            String sansAccent = supprimerAccents(name);
	            if(!sansAccent.matches(nameRegex)){
	                erreurs.put("erName", "Name invalide" + cart.getName() + " FROM " + cart.getId());
	            }
	        }
			
			String price = Double.toString(cart.getPrice());
			if(price == null){
	            erreurs.put("erPrice", "Price obligatoire" + " FROM " + cart.getId());
			} else {
				if(!price.matches(chiffreRegex) && cart.getPrice() < 0){
	                erreurs.put("erPrice", "Price invalide" + cart.getPrice() + " FROM " + cart.getId());
	            }
	        }
			
			String quantity = Double.toString(cart.getQuantity());
			if(quantity == null){
	            erreurs.put("erQuantity", "Quantity obligatoire" + " FROM " + cart.getId());
			} else {
	            if(!quantity.matches(quantityRegex) && cart.getQuantity() < 0 ){
	                erreurs.put("erQuantity", "Quantity invalide" + cart.getQuantity() + " FROM " + cart.getId());
	            }
	        }
			
			total += cart.getTotal();
			
		}
		
		if(!erreurs.isEmpty()){
        	ObjectMapper jsonMapper = new ObjectMapper();
        	String erreursJson = jsonMapper.writeValueAsString(erreurs);
            throw new Exception (erreursJson); // Stop here if the HashMap is not empty
        }

		return total; // executed only if all is fine, Aspect will catch it to create the Json Cart Shop
	}
	
}
