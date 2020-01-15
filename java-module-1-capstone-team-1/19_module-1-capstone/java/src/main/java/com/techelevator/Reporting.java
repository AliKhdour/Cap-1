package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

public class Reporting {
	FileWriter auditFile;
	File salesLogFile;
	PrintWriter outputLog;
	PrintWriter outputSalesReport;
	
	TreeMap <String, Integer> salesReportMap;
	
	
	public Reporting() throws IOException {
		
		auditFile =  new FileWriter("Log.txt", true);

		salesLogFile =  new File(System.getProperty("user.dir"), "Sales_Report.txt");
		salesLogFile.createNewFile();
		
		outputLog = new PrintWriter(auditFile);
		outputSalesReport = new PrintWriter(salesLogFile);

	}
	
	public String getTime() {
		DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
    	String dateString2 = dateFormat2.format(new Date()).toString();
    	
		return dateString2;
	}
	public void createSalesReport(VendingMachine machine) {
		
		for(String key: machine.getSalesReportMap().keySet()) {
			outputSalesReport.println(key +  "|" + machine.getSalesReportMap().get(key));
		}
		outputSalesReport.close();
		
	}
	public void createAuditEntry(String event) {
		//time = get-time
		String format = "%1$-10s%2$-1s\n";
		outputLog.printf(format, getTime().toString(), " " + event);
		outputLog.close();
		//outputSalesReport.println(event);
	}
}
