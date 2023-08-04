package com.exercice.serialize;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.exercice.entity.ProductEntity;

/**
 * Handles serialization of ProductEntity objects. <br/>
 * This class contains a static method to serialize a list of ProductEntity objects and store them in a file named "product.ser".
 */
public class Serialization {
    
    /**
     * Serialize a list of ProductEntity objects and store them in a file named "product.ser".
     * The method creates a list of ProductEntity objects, serializes it, and writes it to a file using ObjectOutputStream.
     */
    public static void serializeProduct() {
        try {
            // Create a list of ProductEntity objects
            List<ProductEntity> products = new ArrayList<>();
            products.add(new ProductEntity(1, "Pen", 1.50));
            products.add(new ProductEntity(2, "Notebook", 250.99));
            products.add(new ProductEntity(3, "Pencil", 0.99));
            products.add(new ProductEntity(4, "Book", 16.0));
            
            // Serialize the list of ProductEntity objects and write it to "product.ser" file
            FileOutputStream fileOut = new FileOutputStream("product.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(products);
            out.close();
            fileOut.close();
            
            // Print a message indicating successful serialization
            System.out.println("Object serialized into the file product.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
