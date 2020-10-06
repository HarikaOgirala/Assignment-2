package com.designpatterns.assignment2;

public class CreditAccount extends Account {

	private double creditBalance;
	private String identifier;

	public CreditAccount(String account_type, double balance, String identifier) {
		super(account_type, balance, account_type);
		this.creditBalance = balance;
		this.identifier=identifier;
	}

	public void deduct(double amount) throws InsufficientFundsException
	{

		System.out.println("Account type:"+this.getAccount_type()+"  "+identifier+"   Balance:"+this.creditBalance);


		if(amount <= this.creditBalance) {
			this.creditBalance = this.creditBalance - amount;
			this.setBalance(creditBalance);
			if(this.creditBalance==0)
				ToggleableSensor(this.identifier); // to check
		}
		else {
			amount = amount - creditBalance;
			if(amount >= 0)
			{
				this.creditBalance = 0;
				super.ToggleableSensor(this.identifier); //to check
				this.setBalance(creditBalance);
				if(amount != 0 && creditBalance == 0) {

					if (this.nextHandler !=null)
					{
						System.out.println("moving to next acc " +this.nextHandler.getAccount_type());
						this.nextHandler.deduct(amount);
					}
					else
						throw new InsufficientFundsException("You have insufficient funds in your accounts");	
				}
			}
		}

	}

	public void charge(double amount)
	{
		 //yet to be implemented
	}

}
