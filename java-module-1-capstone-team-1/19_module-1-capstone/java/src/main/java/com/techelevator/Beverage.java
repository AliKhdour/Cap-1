package com.techelevator;

public class Beverage extends Product{
	
	Beverage(){
		
	}
	public Beverage(String type, String name) {
		super(type, name);
	}
	
	public void message() {
		System.out.println("\nGlug Glug, Yum!");
	}
}
