package com.designpatterns.assignment2;

public class CreditAccount extends Account {

	private Account chain;
	private double creditBalance;
	private String identifier;

	public CreditAccount(String account_type, double balance, String identifier) {
		super(account_type, balance, account_type);
		this.creditBalance = balance;
		this.identifier=identifier;
	}

	public void setNextChain(Account nextChain)
	{
		this.chain=nextChain;
	}


	public void deduct(double amount) throws InsufficientFundsException
	{

		System.out.println("Account type:"+this.getAccount_type()+"  "+identifier+"   Balance:"+this.creditBalance);


		if(amount <= this.creditBalance) {
			this.creditBalance = this.creditBalance - amount;
			if(this.creditBalance==0)
				super.ToggleableSensor(this.identifier);
			System.out.println("After deduct - Account type:"+this.getAccount_type()+"   "+identifier+"   Balance :"+this.creditBalance);
		}
		else {
			amount = amount - creditBalance;
			if(amount >= 0)
			{
				this.creditBalance = 0;
				super.ToggleableSensor(this.identifier);
				if(amount != 0 && creditBalance == 0) {

					if (this.chain !=null)
					{
						System.out.println("moving to next acc " +this.chain.getAccount_type());
						this.chain.deduct(amount);
						System.out.println("After deduct - Account type:"+this.getAccount_type()+"   " +identifier+"   Balance :"+this.creditBalance);
					}
					else
						throw new InsufficientFundsException("You have insufficient funds in your accounts");	
				}
			}
		}



		//System.out.println("creditAccountBalance after deduct :"+creditBalance);

	}

	public void charge(double amount)
	{
		 //yet to be implemented
	}

}
