package com.designpatterns.assignment2;

public class BankAccount extends Account {

	private double bankAccountBalance;
	private Account chain;
	private String identifier;

	public BankAccount(String account_type, double balance, String identifier) {
		super(account_type, balance, account_type);

		this.bankAccountBalance = balance;
		this.identifier=identifier;
	}

	public void setNextChain(Account nextChain)
	{
		this.chain=nextChain;
	}

	public void deduct(double amount) throws InsufficientFundsException
	{
		System.out.println("Account type:"+this.getAccount_type()+"  "+identifier+"   Balance:"+bankAccountBalance);

		if(amount <= this.bankAccountBalance) {
			this.bankAccountBalance = bankAccountBalance - amount;
			if(this.bankAccountBalance == 0)
			{
				super.ToggleableSensor(this.identifier);
			}
			System.out.println("After deduct - Account type:"+this.getAccount_type()+"  "+identifier+"   Balance :"+this.bankAccountBalance);

		}
		else {
			amount = amount - this.bankAccountBalance;
			if(amount >= 0)
			{
				this.bankAccountBalance = 0;
				super.ToggleableSensor(this.identifier);
				if(amount != 0 && this.bankAccountBalance == 0) {
					System.out.println("moving to next acc: "+this.chain.getAccount_type());
					this.chain.deduct(amount);
				}
			}
			System.out.println("After deduct - Account type:"+this.getAccount_type()+"  "+identifier+"   Balance :"+this.bankAccountBalance);
		}

	}

	public void makeDeposit(double amount)
	{
      //yet to be implemented
	}


}
