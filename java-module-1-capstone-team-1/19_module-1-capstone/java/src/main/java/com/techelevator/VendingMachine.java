package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachine {
	
	private Reporting aReport;
	private double currentBalance = 0;
	private TreeMap<String,Slot> inventoryMap = new TreeMap<String, Slot>();
	private TreeMap <String, Integer> salesReportMap;

	private Slot aSlot;
	Scanner userInput = new Scanner(System.in);
	
	
	public VendingMachine() throws FileNotFoundException {
		importInventoryFile();
	}
	
	
	public TreeMap<String, Slot> getTreeMap() {
		return inventoryMap;
	}
	

	public double getCurrentBalance() {
		return currentBalance;
	}

	public TreeMap<String, Slot> getInventoryMap() {
		return inventoryMap;
	}

	public TreeMap<String, Integer> getSalesReportMap() {
		return salesReportMap;
	}
	public Slot getaSlot() {
		return aSlot;
	}


	public Scanner getUserInput() {
		return userInput;
	}
	public void setTreeMap(String itemKey) {
		inventoryMap.get(itemKey).setNumOfItems(1);
	}
	public void setSalesReportTreeMap(String itemKey) {
		int itemQuantitySold = salesReportMap.get(itemKey);
		salesReportMap.put(itemKey, itemQuantitySold + 1);
	}
	public void emptyMachineBalance(double moneyReturned) {
		double previousBalance = currentBalance;
		double endBalance =  previousBalance - moneyReturned;
		
		this.currentBalance = this.currentBalance - moneyReturned;
		aReport.createAuditEntry("GIVE CHANGE: " + " $" + previousBalance + " $"  + endBalance);

	}
	public void removeMoneyFromBalance(double itemPrice) {
		if(currentBalance >= itemPrice) {
			this.currentBalance = this.currentBalance - itemPrice;
		}
		else {
			System.out.println("Sorry, not enough money in machine, please deposit more money");
		}
	}
	public void printInventory() {
		
		String format = "|%1$-10s|%2$-20s|%3$-10s|%4$-5s|\n";
		System.out.println("__________________________________________________" );
		System.out.format(format, "Slot #", "Item Name", "Quantity", "Price" );
		System.out.println("__________________________________________________" );
		for(String key : inventoryMap.keySet() ) {
			String itemQuantity;
			
			if(inventoryMap.get(key).getNumOfItems() <= 0) {
				itemQuantity = "SOLD OUT";
			}
			else {
				itemQuantity = Integer.toString(inventoryMap.get(key).getNumOfItems() ); 
			}
			System.out.format(format, inventoryMap.get(key).getSlotID(), inventoryMap.get(key).getProduct().getNameOfProduct(), itemQuantity, inventoryMap.get(key).getPrice() );
		}
		System.out.println("__________________________________________________" );
	}
	public void dispenseProduct(Product productName) {
		
		//changeInventory();
		//returnMoney();
		productName.message();
	}
	public void requestMoney() throws IOException {
		System.out.println("Insert a $1, $2, $5, $10");
		
		
		int billInserted = Integer.parseInt(userInput.nextLine() );
		
		while( !(billInserted == 1 || billInserted == 2 || billInserted == 5 || billInserted == 10) ) {
			System.out.println("Must insert a $1, $2, $5, or $10 bill");
			billInserted = Integer.parseInt(userInput.nextLine() );

		}
		
		feedMoney(billInserted);
		
	}
	public void feedMoney(int billInserted) throws IOException {
		aReport = new Reporting();
		double previousBalance = this.currentBalance;
		this.currentBalance += billInserted;
		aReport.createAuditEntry("FEED MONEY: " + " $" + previousBalance + " $"  + this.currentBalance);

		System.out.printf("\nCurrent Money Provided: $%.2f\n", currentBalance);
	}
	public void importInventoryFile() throws FileNotFoundException {
		File theFile = new File("vendingmachine.csv");
		Scanner fileScanner = new Scanner(theFile);
		salesReportMap = new TreeMap<String, Integer>();
		
		while(fileScanner.hasNextLine()) {
			String aLine = fileScanner.nextLine();
			String[] anArray = aLine.split("[|]+");
			
			switch(anArray[3]){
				case "Chip":
				aSlot = new Slot(anArray[0], Double.parseDouble(anArray[2]), new Chips(anArray[3], anArray[1]) );
				break;
				case "Candy":
				aSlot = new Slot(anArray[0], Double.parseDouble(anArray[2]), new Candy(anArray[3], anArray[1]) );
				break;
				case "Drink":
				aSlot = new Slot(anArray[0], Double.parseDouble(anArray[2]), new Beverage(anArray[3], anArray[1]) );
				break;
				case "Gum":
				aSlot = new Slot(anArray[0], Double.parseDouble(anArray[2]), new Chips(anArray[3], anArray[1]) );
				break;
				
			}
			this.salesReportMap.put(anArray[1], 0);
			
			this.inventoryMap.put(anArray[0],aSlot);
		
		}
		
	}

	
}
