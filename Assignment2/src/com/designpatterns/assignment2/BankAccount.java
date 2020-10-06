package com.designpatterns.assignment2;

public class BankAccount extends Account {

	private double bankAccountBalance;
	private String identifier;

	public BankAccount(String account_type, double balance, String identifier) {
		super(account_type, balance, account_type);

		this.bankAccountBalance = balance;
		this.identifier=identifier;
	}

	public void deduct(double amount) throws InsufficientFundsException
	{
		System.out.println("Account type:"+this.getAccount_type()+"  "+identifier+"   Balance:"+bankAccountBalance);

		if(amount <= this.bankAccountBalance) {
			this.bankAccountBalance = bankAccountBalance - amount;
			this.setBalance(bankAccountBalance);
			if(this.bankAccountBalance == 0)
			{
				super.ToggleableSensor(this.identifier); // To check -- if super keyword needed, same with CreditAccount class too
			}
			

		}
		else {
			amount = amount - this.bankAccountBalance;
			if(amount >= 0)
			{
				this.bankAccountBalance = 0;
				this.setBalance(bankAccountBalance);
				ToggleableSensor(this.identifier); // To check -- if super keyword needed
				if(amount != 0 && this.bankAccountBalance == 0) {
					System.out.println("moving to next acc: "+this.nextHandler.getAccount_type());
					nextHandler.deduct(amount);
				}
			}
		}

	}

	public void makeDeposit(double amount)
	{
      //yet to be implemented
	}


}
