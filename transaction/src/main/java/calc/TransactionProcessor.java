package main.java.calc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import main.java.basic.Transaction;

public class TransactionProcessor {

	public static List<Transaction> process(List<Transaction> transactions) {

		for (Transaction transaction : transactions) {
			calculateProcessingFee(transaction);
		}
		calculateTransactionFees(transactions);
		return transactions;
	}

	private static void calculateProcessingFee(Transaction transaction) {
		if (transaction.getPriority() == true)
			transaction.setProcessingFee(500);
		else if (transaction.getTransactionType().equalsIgnoreCase("SELL") || transaction.getTransactionType().equalsIgnoreCase("WITHDRAW"))
			transaction.setProcessingFee(100);
		else
			transaction.setProcessingFee(50);
	}

	private static void calculateTransactionFees(List<Transaction> transactions) {
		HashSet<Integer> intraDayTransactionIds = findTransaction(transactions);

		for (Transaction transaction : transactions) {
			if (intraDayTransactionIds.contains(transaction.getTransactionId())) {
				transaction.setProcessingFee(transaction.getProcessingFee() + 10);
				intraDayTransactionIds.remove(transaction.getTransactionId());
			}
		}

	}

	private static HashSet<Integer> findTransaction(List<Transaction> transactions) {
		HashSet<Integer> buySellTransactionIds = new HashSet<Integer>();

		Map<String, Integer> buySellTransactions = new HashMap<String, Integer>();

		for (Transaction transaction : transactions) {
			String key = String.valueOf(transaction.getClientId()) + "_" + String.valueOf(transaction.getSecurityId()) + "_"
					+ String.valueOf(transaction.getDate());

			if (transaction.getTransactionType().contains("BUY")) {
				String findKey = key + "_SELL";

				if (buySellTransactions.containsKey(findKey)) {
					int sellTransactionId = buySellTransactions.get(findKey);
					int buyTransactionId = transaction.getTransactionId();

					buySellTransactionIds.add(sellTransactionId);
					buySellTransactionIds.add(buyTransactionId);
					buySellTransactions.remove(findKey);
				} else {
					key += "_BUY";
					buySellTransactions.put(key, transaction.getTransactionId());
				}
			} else if (transaction.getTransactionType().contains("SELL")) {
				String findKey = key + "_BUY";

				if (buySellTransactions.containsKey(findKey)) {
					int buyTransactionId = buySellTransactions.get(findKey);
					int sellTransactionId = transaction.getTransactionId();

					buySellTransactionIds.add(buyTransactionId);
					buySellTransactionIds.add(sellTransactionId);
					buySellTransactions.remove(findKey);
				} else {
					key += "_SELL";
					buySellTransactions.put(key, transaction.getTransactionId());
				}
			}
		}
		return buySellTransactionIds;
	}
}