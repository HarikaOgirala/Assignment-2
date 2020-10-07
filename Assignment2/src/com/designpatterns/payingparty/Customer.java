package com.designpatterns.payingparty;
import java.util.ArrayList;

import com.designpatterns.accounts.Account;
import com.designpatterns.accounts.BankAccount;
import com.designpatterns.sensors.Led;
import com.designpatterns.sensors.ToggleableSensor;

public class Customer implements PayingParty {

	ArrayList<Account> accounts = new ArrayList<Account>();
	CheckProcessor checkIfPayable =new CheckProcessor();
	boolean payable=false;
	ToggleableSensor sensor = new Led(checkIfPayable.identifier);

	public Customer(Account acc) {
		accounts.add(acc);
	}

	public void addAccount(Account acc)
	{
		accounts.add(acc);
	}

	/**
	 * display all accounts a customer holds
	 */
	public void accountsHeld() {
		for(Account acc:accounts)
		{
			System.out.println(acc.getAccountType()+ " : "+acc.getBalance());
			acc.status();
		}

	}

	/**
	 *@param amount
	 * invokes the procesCheck() method after setting the CoR using setNextHandlers() 
	 *
	 */
	public void pay(double amount) {

		if(accounts.get(0).getAccountType().equalsIgnoreCase("checking"))
		{
			accounts.get(0).setNextHandlers(accounts);
			try {
				payable=checkIfPayable.processCheck((BankAccount) accounts.get(0),amount);
				//to check -- role of payable after processCheck returning true or false??
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	/**
	 * invokes the toggle method in Led class
	 * and calls the getSatus() method of the same class
	 */
	public void toggle()
	{
		sensor.toggle();
		status();
	}
	
	/**
	 * print the status of Led sensor
	 */
	public void status() {
		System.out.println("Led Sensor: "+sensor.getStatus());
	}


}
