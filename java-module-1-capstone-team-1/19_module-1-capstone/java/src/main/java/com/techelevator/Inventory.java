package com.techelevator;

import java.util.ArrayList;

public class Inventory {
	public static ArrayList<Slot> slotArray = new ArrayList<Slot>();
	
	Inventory(ArrayList<Slot> inputArray){
		this.slotArray = inputArray;
	}
	
	public ArrayList<Slot> getSlotArray() {
		return slotArray;
	}

	public void printInventory() {
		for(Slot slot : slotArray) {
			System.out.println(slot.getSlotID() + " " + slot.getProduct().getNameOfProduct() + " " + slot.getNumOfItems() + " " + slot.getPrice() );
		}
	}
}
