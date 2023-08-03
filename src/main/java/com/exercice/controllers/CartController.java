package com.exercice.controllers;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

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
	
	
	public CartController () {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("")
	public void createJsonCart() {
		// mettre le service qui va tout executer
		List<ProductEntity> products = null;
				
		try {
			Serialization.serializeProduct();
			
			FileInputStream fileIn = new FileInputStream("product.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            
            
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                products = (List<ProductEntity>) obj;
            } else {
                // Gérer le cas où l'objet n'est pas une liste de ProductEntity
            	in.close();
            	fileIn.close();
            	throw new IllegalArgumentException("L'objet désérialisé n'est pas une liste de ProductEntity.");
            }
            
            
            
            in.close();
            fileIn.close();
            
            System.out.println("Objet désérialisé :");
            
            List<Cart> carts = new ArrayList<>();
 
            for (ProductEntity product : products) {
                Cart c1 = new Cart(product.getId(), product.getName(), product.getPrice());
                carts.add(c1); // Ajoutez le panier à la liste
            }
            
            double resultat = verificationService.Verification(carts);
			this.LOG.info("le prix total du Controller est de : " + resultat + " euros.");
            
		} catch (Exception e) {
            e.printStackTrace();
            System.err.println("Une erreur s'est produite lors de l'écriture du fichier : " + e.getMessage());
        }
		
		
	}
	
}
