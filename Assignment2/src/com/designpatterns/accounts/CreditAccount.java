package com.designpatterns.accounts;

import com.designpatterns.exceptions.InsufficientFundsException;

public class CreditAccount extends Account {

	private double creditBalance;

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
			setTotalBalance(getTotalBalance() - amount);
			this.creditBalance = this.creditBalance - amount;
			this.setBalance(creditBalance);
			if (this.creditBalance == 0)
				toggle(identifier);
		} else {
			this.setTotalBalance(this.creditBalance);
			amount = amount - creditBalance;
			this.creditBalance = 0;
			if (this.creditBalance == 0 && this.nextHandler != null) {
				toggle(identifier); // to check
				this.setBalance(creditBalance);
				System.out.println("next account in chain: " + this.nextHandler.getAccountType());
				this.nextHandler.deduct(amount);
			} else
				throw new InsufficientFundsException();
		}

	}

	public double creditlimit(double amount)
	{
		return this.creditLimit = amount;
	}

	public void charge(double amount) throws InsufficientFundsException
	{
		try {
			if((amount+this.balance) < creditLimit)
			{
				this.balance = amount+this.balance;
			}
			else {
				throw new InsufficientFundsException();
			}
		}
		
		catch(InsufficientFundsException e) {
			System.out.println("In credit account====" + e.toString());
			
		}
		
			
	}
}
