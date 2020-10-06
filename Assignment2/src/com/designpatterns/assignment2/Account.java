package com.designpatterns.assignment2;
import java.util.ArrayList;

import com.designpatterns.sensors.Led;

public abstract class Account {

	private String account_type;
	private double balance;
	private String identifier;
	protected Account nextHandler;	
	
	public Account(String account_type, double balance, String identifier) {
		super();
		this.account_type = account_type;
		this.balance = balance;
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public abstract void deduct(double amount) throws InsufficientFundsException;
	
	protected  void setNextHandler(Account chain)
	{
		this.nextHandler = chain;
	}
	
    protected void setNextHandlers(ArrayList<Account> handlers) {

	for (int i = 0; i < handlers.size() - 1; i++) {
		handlers.get(i).setNextHandler(handlers.get(i + 1));

	}
	}
	
	public void ToggleableSensor(String identifier)
	{
		//Led ON when balance is zero
		Led testLed=new Led(identifier);
		testLed.turnOn();
		System.out.println(testLed.toString());	
	}
	
}
