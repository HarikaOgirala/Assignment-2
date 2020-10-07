package com.designpatterns.accounts;

import com.designpatterns.exceptions.InsufficientFundsException;

public class CreditAccount extends Account {

	private double creditBalance;

	/**
	 * parameterized constructor
	 * @param accountType
	 * @param balance
	 * @param identifier
	 */
	public CreditAccount(String accountType, double balance, String identifier) {
		super(accountType, balance, accountType);
		this.creditBalance = balance;
	}

	/**
	 * CoR method
	 * @param amount
	 * @throws InsufficientFundsException
	 */
	public void deduct(double amount) throws InsufficientFundsException
	{
		//print current account details
		System.out.println(this.getAccountType()+ " : "+this.creditBalance+"\t due :"+ amount );

		if(amount <= this.creditBalance) {
			this.creditBalance = this.creditBalance - amount;
			this.setBalance(creditBalance);
			if(this.creditBalance==0)
				toggle(); // to check
		}
		else {
			amount = amount - creditBalance;
			if(amount >= 0)
			{
				this.creditBalance = 0;
				toggle(); //to check
				this.setBalance(creditBalance);
				if(amount != 0 && creditBalance == 0) {

					if (this.nextHandler !=null)
					{
						System.out.println("next account in chain: " +this.nextHandler.getAccountType());
						this.nextHandler.deduct(amount);
					}
					else
						throw new InsufficientFundsException();	
				}
			}
		}
	}

	public void charge(double amount)
	{
		//yet to be implemented
	}

}
