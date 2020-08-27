package main.java;

import java.util.List;
import java.util.Scanner;

import main.java.basic.Transaction;
import main.java.calc.OrganiseTransactions;
import main.java.calc.TransactionProcessor;
import main.java.util.CsvIO;

public class TransactionFeeCalculator {

	public static void main(String[] args) {
		
		Scanner read = new Scanner(System.in);

		System.out.println("Write Input File name : "); 	
		String inputFile = read.nextLine();

		System.out.println("Write Output File name : ");	
		String outputFile = read.nextLine();

		List<Transaction> transactions = CsvIO.readCSV(inputFile);
		transactions = TransactionProcessor.process(transactions);

		List<Transaction> groupedTransactions = OrganiseTransactions.group(transactions);
		CsvIO.writeCSV(groupedTransactions, outputFile);
		
		read.close();
		
	}
}
