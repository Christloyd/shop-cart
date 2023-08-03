package com.exercice.controllers;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercice.Serialization;
import com.exercice.entity.ProductEntity;



@RestController
@RequestMapping("/cart")
public class CartController extends AbstractController {

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
            
            if (products != null) {
            	
            	FileWriter writer = new FileWriter("src/main/resources/fichierTransporte.txt");

                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write("Product\t\tQuantity\t\tPrice\t\tTotal\t\t");
                bufferedWriter.newLine();
                bufferedWriter.write("------------------------------------------------");
                bufferedWriter.newLine();
            	
            	for (ProductEntity product : products) {
            		bufferedWriter.write(product.getName() + "\t\t" + "Quantity" + "\t\t "+ product.getPrice() + "\t\tTotal\t\t\n");
            		System.out.println("id : " + product.getId());
		            System.out.println("name : " + product.getName());
		            System.out.println("prix : " + product.getPrice());
            	}
            	
            	bufferedWriter.close();
            }
		} catch (Exception e) {
            e.printStackTrace();
            System.err.println("Une erreur s'est produite lors de l'écriture du fichier : " + e.getMessage());
        }
		
		
	}
	
}
