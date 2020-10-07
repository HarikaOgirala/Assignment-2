package com.designpatterns.accounts;
import java.util.ArrayList;

import com.designpatterns.exceptions.InsufficientFundsException;
import com.designpatterns.sensors.ToggleableSensor;
import com.designpatterns.sensors.Led;

/**
 * @author nagah
 *
 */
public abstract class Account {

	private String accountType;
	private double balance;
	private String identifier;
	protected Account nextHandler;	
	protected static double totalBalance = 0;
	ToggleableSensor sensor = new Led(identifier);

	public Account(String accountType, double balance, String identifier) {
		super();
		this.accountType = accountType;
		this.balance = balance;
		this.identifier = identifier;
		setTotalBalance();
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * creates the sum of all available balances for the given list of accounts
	 * invoked in constructor
	 */
	public void setTotalBalance() {
		totalBalance = totalBalance + balance;
	}
	
	/**
	 * abstract method that initiates the CoR Design Pattern
	 * @param amount
	 * @throws InsufficientFundsException
	 */
	public abstract void deduct(double amount) throws InsufficientFundsException;

	/**
	 * creates a chain of account to sustain the order of accounts
	 * @param handlers
	 */
	public void setNextHandlers(ArrayList<Account> handlers) {

		for (int i = 0; i < handlers.size() - 1; i++) {
			handlers.get(i).nextHandler = handlers.get(i + 1);
		}
	}


	/**
	 * invokes the toggle method in Led class
	 * and calls the getSatus() method of the same class
	 */
	public void toggle()
	{
		//Led ON when balance is zero
		sensor.toggle();
		status();	
	}

	/**
	 * print the status of Led sensor
	 */
	public void status() {
		System.out.println("Led Sensor: "+sensor.getStatus());
	}
}
