package com.techelevator;

public class Gum extends Product{

	public Gum() {
		
	}
	public Gum(String type, String name) {
		super(type, name);
	}
	public void message() {
		System.out.println("\nChew Chew, Yum!");
	}
}
