package com.techelevator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class Purchase {
	
	//Purchase Menu options and arraylist
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION };
	
	private Reporting aReport;
	private Menu vendingMenu;
	private VendingMachine theMachine;
	Menu appMenu = new Menu(System.in, System.out);                // Instantiate a menu for Vending Machine to use
	
	Scanner userInput = new Scanner(System.in);

	public Purchase(Menu menu, VendingMachine machine) throws FileNotFoundException {  		// Constructor - user will pas a menu for this class to use
		this.theMachine = machine;
		this.vendingMenu = menu;           							// Make the Menu the user object passed, our Menu
	}
	
	public void presentMenu() throws IOException {
		System.out.printf("\nCurrent Money Provided: $%.2f\n", theMachine.getCurrentBalance());
		boolean shouldProcess = true;         						// Loop control variable
		
		while(shouldProcess) {                						// Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  						// Process based on user menu choice
			
				case PURCHASE_MENU_FEED_MONEY:
					requestMoney();          			// invoke method to display items in Vending Machine
					break;                    						// Exit switch statement
			
				case PURCHASE_MENU_SELECT_PRODUCT:
					dispenseProduct();
					         										// invoke method to purchase items from Vending Machine
					break;                    						// Exit switch statement
			
				case PURCHASE_MENU_FINISH_TRANSACTION:
					finishTransaction();   										// Invoke method to perform end of method processing
					shouldProcess = false;   					// Set variable to end loop
					break;                    					// Exit switch statement
			}	
		}
		return;                              					// End method and return to caller
	}
	
	public void dispenseProduct() throws IOException {
		aReport = new Reporting();
		theMachine.printInventory();
		System.out.printf("\nCurrent Money Provided: $%.2f\n", theMachine.getCurrentBalance());
		System.out.print("\nPlease enter the item number that you would like to select: ");
		String itemChoice = userInput.nextLine();
		itemChoice = itemChoice.toUpperCase();
		int numberLeftOfItem = theMachine.getTreeMap().get(itemChoice).getNumOfItems(); // NEW NAMES
		String itemName = theMachine.getTreeMap().get(itemChoice).getProduct().getNameOfProduct(); // NEW NAMES
		double machineBalance =	theMachine.getCurrentBalance(); // NEW NAMES
		double itemCost = theMachine.getTreeMap().get(itemChoice).getPrice();// NEW NAMES
		String slotID = theMachine.getTreeMap().get(itemChoice).getSlotID(); //NEW NAMES
		
		if(theMachine.getTreeMap().containsKey(itemChoice) ) {
			
			
			
			if(numberLeftOfItem == 0) {
				System.out.println("\nYou chose " +  itemName);
				System.out.println("This product is SOLD OUT, please select another item.");
			}
			else {
				
				
				
				if (machineBalance >= itemCost) { //Checking to see if there's enough money
					double currentBalance = machineBalance;
					double itemPrice = itemCost;
					double afterBalance = currentBalance - itemPrice;
					
					
					theMachine.removeMoneyFromBalance(itemPrice); //removes money from balance IF there's enough money in machine
					theMachine.setTreeMap(itemChoice);	//removes 1 from the quantity (Slot attribute) of the item chosen
					System.out.printf("\nYou chose " + itemName + " and it cost $%.2f\n", itemCost );
					theMachine.getTreeMap().get(itemChoice).getProduct().message();
					
					theMachine.setSalesReportTreeMap(itemName);
					aReport.createAuditEntry(itemName + " " + slotID + " $" + currentBalance + " $" + afterBalance);
					System.out.printf("\nCurrent Money Remaining: $%.2f\n", theMachine.getCurrentBalance());
				}
				else {
					System.out.println("\nSorry, not enough money in machine, please deposit more money.");
				}
			}
		} 
		else {
			System.out.println("\nYou chose an incorrect Slot#, please try again.");
		}
		
	
	}
	public void finishTransaction() {
		double machineBalance =	theMachine.getCurrentBalance(); // NEW NAMES
		double theBalance = machineBalance ;
		double originalBalance = theBalance;
		double n = .05;
		double q = .25;
		double d = .10;
		int counterQ = 0;
		int counterN = 0;
		int counterD = 0;
		
		while (theBalance >= q) {
			theBalance -= q;
			counterQ++;
		}
		
		while (theBalance >= d) {
			theBalance -= d;
			counterD++;
		}
		
		while (theBalance >= n) {
			theBalance -= n;
			counterN++;
		}
		System.out.printf("Total money returned : $%.2f\n", originalBalance);
		System.out.println("\nQuarters:" +  counterQ + "\n");
		System.out.println("Dimes:" +  counterD + "\n");
		System.out.println("Nickles:" +  counterN + "\n");
		
		theMachine.emptyMachineBalance(originalBalance);
		

	}
	public void requestMoney() throws IOException {
		System.out.println("Insert a $1, $2, $5, $10");
		int billInserted = 0;
		
		try {
			billInserted = Integer.parseInt(userInput.nextLine() );
		}
		catch(Exception e){
			System.out.println("\nMUST enter a whole dollar amount ($1, $2, $5, $10)");
		}
		while( !(billInserted == 1 || billInserted == 2 || billInserted == 5 || billInserted == 10) ) {
			System.out.println("\nMust insert a $1, $2, $5, or $10 bill");
			try {
				billInserted = Integer.parseInt(userInput.nextLine() );
			}
			catch(Exception e){
				System.out.println("\nMUST enter a whole dollar amount ($1, $2, $5, $10");
			}
		}
		
		theMachine.feedMoney(billInserted);
		
	}
	

}
