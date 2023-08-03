package com.exercice.http;

import java.util.Random;

public class Cart {
	Random random = new Random();

	private int id;
	private String name;
	private int quantity = random.nextInt(10)+1;
	private double price;
	
	
	public Cart(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getPrice() {
		return price;
	}
	
	public double getTotal() {
		return getQuantity() * getPrice();
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", total=" + getTotal()
				+ "]";
	}
	
	
	
}
