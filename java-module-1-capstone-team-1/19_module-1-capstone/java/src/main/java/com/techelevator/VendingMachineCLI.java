package com.techelevator;
import java.io.FileNotFoundException;
import java.io.IOException;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;

public class VendingMachineCLI {
	
	
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";// constant strings 
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT          = "Sales Report";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, // array of constants
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_SALES_REPORT,
													    MAIN_MENU_OPTION_EXIT
													    };
	
	private static Reporting aReport;
	private static VendingMachine theMachine; // 
	private static Menu vendingMenu;              // Menu object to be used by an instance of this class
	static Menu appMenu = new Menu(System.in, System.out);                // Instantiate a menu for Vending Machine to use

	public VendingMachineCLI(Menu menu) throws FileNotFoundException {  // Constructor - user will pas a menu for this class to use
		this.theMachine = new VendingMachine();
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
	}
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	 * @throws IOException 
	***************************************************************************************************************************/

	public void run() throws IOException {
		
		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems();           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();          // invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement
				case MAIN_MENU_OPTION_SALES_REPORT:
					salesReport();    // Set variable to end loop
					break; 
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public static void displayItems() {      // static attribute used as method is not associated with specific object instance
		theMachine.printInventory();
	
	}
	
	public static void purchaseItems() throws IOException {	 // static attribute used as method is not associated with specific object instance
		Purchase purchase = new Purchase(appMenu, theMachine);
		purchase.presentMenu();
		
	}
	
	public static void endMethodProcessing() throws IOException {
		aReport = new Reporting();
		aReport.createSalesReport(theMachine);
	}
	public static void salesReport() throws IOException { // static attribute used as method is not associated with specific object instance
		aReport = new Reporting();
		aReport.createSalesReport(theMachine);
	}
}
