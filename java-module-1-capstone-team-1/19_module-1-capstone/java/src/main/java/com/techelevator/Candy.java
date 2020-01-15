package com.techelevator;

public class Candy extends Product {
	
	public Candy() {
		
	}
	public Candy(String type, String name) {
		super(type, name);
	}
	public void message() {
		System.out.println("\nMunch Munch, Yum!");
	}
}
