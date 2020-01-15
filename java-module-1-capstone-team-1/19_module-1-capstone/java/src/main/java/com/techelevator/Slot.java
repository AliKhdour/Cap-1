package com.techelevator;


public class Slot {
	
	private double price;
	private int numOfItems;
	private String slotID;
	private Product product;
	
	Slot(){
		
	}
	Slot(String id, double price, Product product){
		this.price = price;
		this.numOfItems = 5;
		this.slotID = id;
		this.product = product;
		
	}
	
	public double getPrice() {
		return price;
	}


	public int getNumOfItems() {
		return numOfItems;
	}
	public void setNumOfItems(int numToRemove) {
		numOfItems = numOfItems - numToRemove;
	}
	public String getSlotID() {
		return slotID;
	}


	public Product getProduct() {
		return product;
	}


	
}
