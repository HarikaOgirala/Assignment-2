package com.designpatterns.accounts;

import java.util.ArrayList;

import com.designpatterns.exceptions.InsufficientFundsException;
import com.designpatterns.sensors.ToggleableSensor;
import com.designpatterns.sensors.Led;

public abstract class Account {

	private String accountType;
	protected double balance;
	protected double creditLimit;
	protected String identifier;
	protected Account nextHandler;
	protected static double totalBalance = 0;
	protected String identifier1;
	protected Account Type = null;

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

	public void setTotalBalance(double totalBal) {
		totalBalance = totalBal;
	}

	public double getTotalBalance() {
		return totalBalance;
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
	 * 
	 * @param amount
	 * @throws InsufficientFundsException
	 */
	public abstract void deduct(double amount) throws InsufficientFundsException;

	/**
	 * creates a chain of account to sustain the order of accounts
	 * 
	 * @param handlers
	 */
	public void setNextHandlers(ArrayList<Account> handlers) {

		for (int i = 0; i < handlers.size() - 1; i++) {
			handlers.get(i).nextHandler = handlers.get(i + 1);
		}
	}

	/**
	 * invokes the toggle method in Led class and calls the getStatus() method of
	 * the same class
	 */
	public void toggle(String identifier) {
		// Led ON when balance is zero

		sensor = new Led(identifier);
		sensor.toggle();
		System.out.print(identifier + "'s ");
		status();
	}

	/**
	 * print the status of Led sensor
	 */
	public void status() {
		System.out.println("Led Sensor: " + sensor.getStatus());
	}
}