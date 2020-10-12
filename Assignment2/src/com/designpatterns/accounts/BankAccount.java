package com.designpatterns.accounts;

import com.designpatterns.exceptions.InsufficientFundsException;

public class BankAccount extends Account {

	private double bankAccountBalance;

	/**
	 * parameterized constructor
	 * 
	 * @param accountType
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

	/**
	 * adding the deposit amount to BankAccount balance
	 * 
	 * @param amount
	 */
	public void makeDeposit(double amount) {

		this.balance = amount + this.balance;
		this.setTotalBalance(getTotalBalance() + amount);

		if (this.balance > 0 && (sensor.getStatus().toString() == "On")) {
			System.out.println("Led Sensor : " + sensor.getStatus());
			System.out.println(sensor.toggle());

		}

	}

}
