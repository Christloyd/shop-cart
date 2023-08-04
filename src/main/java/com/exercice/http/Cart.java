package com.exercice.http;

import java.util.Random;

/**
 * Represents a cart. <br/>
 * This class represents a cart with properties such as ID, name, quantity, price, and total.
 */
public class Cart {
    // Random object for generating random quantities
    Random random = new Random();

    private int id; // Cart ID
    private String name; // Cart name
    private double quantity = random.nextInt(10) + 1; // Random quantity between 1 and 10
    private double price; // Cart price

    /**
     * Constructor for creating a cart.
     *
     * @param id    the ID of the cart
     * @param name  the name of the cart
     * @param price the price of the cart
     */
    public Cart(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Get the ID of the cart.
     *
     * @return the cart ID
     */
    public int getId() {
        return id;
    }

    /**
     * Get the name of the cart.
     *
     * @return the cart name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the quantity of items in the cart.
     *
     * @return the quantity of items
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Get the price of the cart.
     *
     * @return the cart price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Calculate the total cost of the items in the cart.
     *
     * @return the total cost
     */
    public double getTotal() {
        return getQuantity() * getPrice();
    }
    
    

    public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
    public String toString() {
        return "Cart [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", total=" + getTotal()
                + "]";
    }

}
