package com.designpatterns.accounts;

import com.designpatterns.exceptions.InsufficientFundsException;

public class CreditAccount extends Account {

	private double creditBalance;
	private final double CREDIT_LIMIT = 100;

	/**
	 * parameterized constructor
	 * 
	 * @param accountType
	 * @param balance
	 * @param identifier
	 */
	public CreditAccount(String accountType, double balance, String identifier) {
		super(accountType, balance, identifier);
		this.creditBalance = balance;
	}

	/**
	 * CoR method
	 * 
	 * @param amount
	 * @throws InsufficientFundsException
	 */
	public void deduct(double amount) throws InsufficientFundsException {
		// print current account details
		System.out.println(this.getAccountType() + " : " + this.creditBalance + "\t due :" + amount);

		if (amount <= this.creditBalance) {
			this.setTotalBalance(getTotalBalance() - amount);
			this.creditBalance = this.creditBalance - amount;
			this.setBalance(creditBalance);
			if (this.creditBalance == 0)
				toggle(identifier);
		} else {
			this.setTotalBalance(this.creditBalance);
			amount = amount - creditBalance;
			this.creditBalance = 0;

			if (this.creditBalance == 0 && this.nextHandler != null) {
				// if (amount < this.getTotalBalance() && this.nextHandler != null) {

				toggle(identifier); // to check
				this.setBalance(creditBalance);
				System.out.println("next account in chain: " + this.nextHandler.getAccountType());
				this.nextHandler.deduct(amount);
			} else
				throw new InsufficientFundsException();
		}

	}

	public void charge(double amount) throws InsufficientFundsException {
		try {
			if ((amount + this.balance) < CREDIT_LIMIT) {
				this.balance = amount + this.balance;
				this.setTotalBalance(totalBalance + amount);
				if (this.balance > 0 && (sensor.getStatus().toString() == "On")) {
					System.out.println("Led Sensor : " + sensor.getStatus());
					System.out.println(sensor.toggle());

				}
			} else {
				throw new InsufficientFundsException();
			}
		}

		catch (InsufficientFundsException e) {
			System.out.println("In credit account====" + e.toString());

		}

	}
}
