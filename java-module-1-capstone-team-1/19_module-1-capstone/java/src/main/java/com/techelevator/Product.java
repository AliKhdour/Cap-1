package com.techelevator;

public class Product {
	
	private String typeOfProduct;
	private String nameOfProduct;
	
	Product(){
		
	}
	Product(String type, String name){
		this.typeOfProduct = type;
		this.nameOfProduct = name;
	}

	public String getTypeOfProduct() {
		return typeOfProduct;
	}

	public String getNameOfProduct() {
		return nameOfProduct;
	}
	
	public void message() {
		System.out.println("****The specific product goes here***");
	}
	
}
