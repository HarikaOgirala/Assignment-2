package com.designpatterns.accounts;

import com.designpatterns.exceptions.InsufficientFundsException;
import com.designpatterns.sensors.Led;

public class BankAccount extends Account {

	private double bankAccountBalance;
	private double totalAmount;

	/**
	 * parameterized constructor
	 * 
	 * @param account_type
	 * @param balance
	 * @param identifier
	 */
	public BankAccount(String accountType, double balance, String identifier) {
		super(accountType, balance, identifier);

		this.bankAccountBalance = balance;
	}

	/**
	 * CoR method
	 * 
	 * @param amount
	 * @throws InsufficientFundsException
	 */
	public void deduct(double amount) throws InsufficientFundsException {
		this.totalAmount = amount;
		System.out.println(this.getAccountType() + " : " + bankAccountBalance + "\t due :" + amount);

		if (amount <= this.bankAccountBalance) {
			setTotalBalance(getTotalBalance() - amount);
			this.bankAccountBalance = bankAccountBalance - amount;
			this.setBalance(bankAccountBalance);
			if (this.bankAccountBalance == 0) {
				toggle(identifier);
			}
		} else {

			if (amount < super.getTotalBalance()) {
				this.setTotalBalance(getTotalBalance() - this.bankAccountBalance);
				amount = amount - this.bankAccountBalance;
				this.bankAccountBalance = 0;
				this.setBalance(bankAccountBalance);
				toggle(identifier);
				System.out.println("next account in chain: " + this.nextHandler.getAccountType());
				nextHandler.deduct(amount);
			} else {
				this.setTotalBalance(amount);
				nextHandler.deduct(amount);
			}
		}
	}

	public void makeDeposit(double amount) {

		this.balance = amount + this.balance;
		this.setTotalBalance(totalBalance + amount);

		if (this.balance > 0 && (sensor.getStatus().toString() == "On")) {
			System.out.println("Led Sensor : " + sensor.getStatus());
			System.out.println(sensor.toggle());

		}

	}

}
