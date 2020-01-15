package com.techelevator;

public class Chips extends Product {
	
	public Chips() {
		
	}
	public Chips(String type, String name) {
		super(type, name);
	}
	public void message() {
		System.out.println("\nCrunch Crunch, Yum!");
	}
}
