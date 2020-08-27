package main.java.basic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.util.TransactionType;

public class transaction {

	private int transactionId;
	private int clientId;
	private int securityId;
	private TransactionType transactionType;
	private Date date;
	private int marketValue;
	private Boolean priority;
	private int processingFee;
	
	public transaction() {
		super();
	}
	public transaction(int transactionId, int clientId, int securityId, TransactionType transactionType, Date date,
			int marketValue, Boolean priority, int processingFee) {
		super();
		this.transactionId = transactionId;
		this.clientId = clientId;
		this.securityId = securityId;
		this.transactionType = transactionType;
		this.date = date;
		this.marketValue = marketValue;
		this.priority = priority;
		this.processingFee = processingFee;
	}
	public int gettransactionId() {
		return transactionId;
	}
	public void settransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getSecurityId() {
		return securityId;
	}
	public void setSecurityId(int securityId) {
		this.securityId = securityId;
	}
	public String gettransactionType() {
		return transactionType.name();
	}
	
	public void setTransactionType(String transType) {
		if (transType == "BUY")
			this.transactionType = TransactionType.BUY;
		else if (transType == "SELL")
			this.transactionType = TransactionType.SELL;
		else if (transType == "DEPOSIT")
			this.transactionType = TransactionType.DEPOSIT;
		else 
			this.transactionType = TransactionType.WITHDRAW;
	}
	
	public String getDate() {
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return format.format(this.date);
	}
	public void setDate(String date) {
		try {
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			this.date = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public int getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(int marketValue) {
		this.marketValue = marketValue;
	}
	public Boolean getPriority() {
		return priority;
	}
	public void setPriority(Boolean priority) {
		this.priority = priority;
	}
	public int getProcessingFee() {
		return processingFee;
	}
	public void setProcessingFee(int processingFee) {
		this.processingFee = processingFee;
	}
}
