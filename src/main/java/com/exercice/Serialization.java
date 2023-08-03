package com.exercice;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.exercice.entity.ProductEntity;

public class Serialization {
	
	public static void serializeProduct() {
		try {

			List<ProductEntity> products = new ArrayList<>();
	        products.add(new ProductEntity(1, "Pen", 1.50));
	        products.add(new ProductEntity(2, "Notebook", 250.99));
	        products.add(new ProductEntity(3, "Pencil", 0.99));
	        products.add(new ProductEntity(4, "Book", 16.0));
			
			FileOutputStream fileOut = new FileOutputStream("product.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(products);
            out.close();
            fileOut.close();
            System.out.println("Objet sérialisé dans le fichier product.ser");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
