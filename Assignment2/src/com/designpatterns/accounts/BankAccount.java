package com.designpatterns.accounts;

import com.designpatterns.exceptions.InsufficientFundsException;

public class BankAccount extends Account {

	private double bankAccountBalance;

	/**
	 * parameterized constructor
	 * @param account_type
	 * @param balance
	 * @param identifier
	 */
	public BankAccount(String accountType, double balance, String identifier) {
		super(accountType, balance, accountType);

		this.bankAccountBalance = balance;
	}

	/**
	 * CoR method
	 * @param amount
	 * @throws InsufficientFundsException
	 */
	@SuppressWarnings("static-access")
	public void deduct(double amount) throws InsufficientFundsException
	{
		System.out.println(this.getAccountType() +" : "+bankAccountBalance+ "\t due :"+ amount);
		
		if(amount > super.totalBalance ) throw new InsufficientFundsException(); 
		else {
			if(amount <= this.bankAccountBalance) {
				this.bankAccountBalance = bankAccountBalance - amount;
				this.setBalance(bankAccountBalance);
				if(this.bankAccountBalance == 0)
				{
					toggle(); // To check -- if super keyword needed, same with CreditAccount class too
				}


			}
			else {
				amount = amount - this.bankAccountBalance;
				if(amount >= 0)
				{
					this.bankAccountBalance = 0;
					this.setBalance(bankAccountBalance);
					toggle(); // To check -- if super keyword needed
					
					if(amount != 0 && this.bankAccountBalance == 0) {
						System.out.println("next account in chain: "+this.nextHandler.getAccountType());
						nextHandler.deduct(amount);
					}
				}
			}
		}
	}

	public void makeDeposit(double amount)
	{
		//yet to be implemented
	}


}
