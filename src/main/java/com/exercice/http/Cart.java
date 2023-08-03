package com.exercice.http;

public class Cart {
	private int id;
	private String name;
	private int quantity;
	private double price;
	private double total = getPrice() * getQuantity();
	
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
		return total;
	}
	
}
