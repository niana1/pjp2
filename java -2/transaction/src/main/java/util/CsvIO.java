package main.java.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import main.java.basic.Transaction;

public class CsvIO {

	
	private static final String CSV_DELIMITER = ",";
	

	private static Function<String, Transaction> csvScanner = (line) -> {
		String[] row = line.split(",");
		Transaction transaction = new Transaction();
		if (row[0] != null && row[0].trim().length() > 0) 			transaction.setTransactionId(Integer.parseInt(row[0]));		
		if (row[1] != null && row[1].trim().length() > 0) 			transaction.setClientId(Integer.parseInt(row[1]));		
		if (row[2] != null && row[2].trim().length() > 0) 			transaction.setSecurityId(Integer.parseInt(row[2]));		
		if (row[3] != null && row[3].trim().length() > 0)			transaction.setTransactionType(row[3]);		
		if (row[4] != null && row[4].trim().length() > 0) 			transaction.setDate(row[4]);		
		if (row[5] != null && row[5].trim().length() > 0) 			transaction.setMarketValue(Integer.parseInt(row[5]));		
		if (row[6] != null && row[6].trim().length() > 0) 			transaction.setPriority(Boolean.parseBoolean(row[6]));
		
		return transaction;
	};
	
	
	public static List<Transaction> readCSV(String filename) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

			transactions = reader.lines().skip(1).map(csvScanner).collect(Collectors.toList());
			reader.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return transactions;	
	}

	public static void writeCSV(List<Transaction> groupedTransactions, String filename) {
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));

			StringBuffer row = new StringBuffer();
			row.append("Clint ID" + CSV_DELIMITER + "Transaction Type" + CSV_DELIMITER + "Transaction Date"
					+ CSV_DELIMITER + "Priority" + CSV_DELIMITER + "Processing Fee");
			writer.write(row.toString());
			writer.newLine();

			for (Transaction transaction : groupedTransactions) {
				row = new StringBuffer();
				row.append(transaction.getClientId());
				row.append(CSV_DELIMITER);
				row.append(transaction.getTransactionType().trim().length() == 0 ? "" : transaction.getTransactionType());
				row.append(CSV_DELIMITER);
				row.append(transaction.getDate().trim().length() == 0 ? "" : transaction.getDate());
				row.append(CSV_DELIMITER);
				row.append(transaction.getPriority());
				row.append(CSV_DELIMITER);
				row.append(transaction.getProcessingFee());
				row.append(CSV_DELIMITER);

				writer.write(row.toString());
				writer.newLine();
			}
			writer.flush();
			writer.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Exception occured due to encoding ");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("Filename entered is not valid");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Exception occured while writing to file ");
			e.printStackTrace();
		}		
	}

}
