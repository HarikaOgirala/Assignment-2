package com.designpatterns.assignment2;
import java.util.ArrayList;

import com.designpatterns.sensors.Led;

public class Customer implements PayingParty {

	ArrayList<Account> accounts = new ArrayList<Account>();
	CheckProcessor checkIfPayable =new CheckProcessor();
	Boolean payable=false;
	
	public Customer(BankAccount acc) {
		accounts.add(acc);
	}
	
	public void addAccount(BankAccount acc)
	{
		accounts.add(acc);
	}
	
	public void addAccount(CreditAccount acc)
	{
		accounts.add(acc);
	}

	
	public void accountsHeld() {
		for(Account acc:accounts)
		{
			System.out.println(acc.getAccount_type()+ " : "+acc.getBalance());
		}
		
	}

	public void pay(double i) {

		if(accounts.get(0).getAccount_type().equalsIgnoreCase("checking"))
		{
			accounts.get(0).setNextHandlers(accounts);
			try {
				payable=checkIfPayable.processCheck((BankAccount) accounts.get(0),i);
				//to check -- role of payable after processCheck returning true or false??
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		
		
		/*if(payable)
		{
			System.out.println("Transaction complete");
		}
		else
		{
			ToggleableSensor();
		}*/
	}
	
	//to check -- still dint get the usage
	public void ToggleableSensor()
	{
		Led testLed=new Led(checkIfPayable.identifier);
		testLed.turnOn();
		System.out.println(testLed.toString());
	}
	
	
}
