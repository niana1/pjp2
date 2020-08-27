	package main.java.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import main.java.basic.Transaction;

public class OrganiseTransactions {

	public static List<Transaction> group(List<Transaction> transactions) {
		
		List<Transaction> groupedTransaction = new ArrayList<Transaction>();
		
		Map<Integer, Map<String, Map<String, Map<Boolean, List<Transaction>>>>> groupedTransactionSet = transactions.stream()
					.collect(Collectors.groupingBy(Transaction::getClientId, 
								Collectors.groupingBy(Transaction::getTransactionType,
										Collectors.groupingBy(Transaction::getDate, 
												Collectors.groupingBy(Transaction::getPriority)))));

		
		

		for (Integer keyA : groupedTransactionSet.keySet()) {
			Map<String, Map<String, Map<Boolean, List<Transaction>>>> m2 = groupedTransactionSet.get(keyA);
			for (String keyB : m2.keySet()) {
				Map<String, Map<Boolean, List<Transaction>>> m3 = m2.get(keyB);
				for (String keyC : m3.keySet()) {
					Map<Boolean, List<Transaction>> m4 = m3.get(keyC);
					for (Boolean keyD : m4.keySet()) {
						List<Transaction> groupedSet = m4.get(keyD);
						groupedTransaction.addAll(groupedSet);
					}
				}
			}
		}

		return groupedTransaction;
	}

}
