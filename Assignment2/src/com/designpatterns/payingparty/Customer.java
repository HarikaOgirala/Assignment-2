package com.designpatterns.payingparty;

import java.util.ArrayList;

import com.designpatterns.accounts.Account;
import com.designpatterns.accounts.BankAccount;
import com.designpatterns.sensors.Led;
import com.designpatterns.sensors.ToggleableSensor;

public class Customer implements PayingParty {

	private ArrayList<Account> accounts = new ArrayList<Account>();
	private CheckProcessor checkIfPayable = new CheckProcessor();
	private boolean payable = false;

	private ToggleableSensor sensor = new Led(checkIfPayable.identifier);

	public Customer() {

	}

	public Customer(Account acc) {
		accounts.add(acc);
	}

	public void addAccount(Account acc) {
		accounts.add(acc);
	}

	/**
	 * display all accounts a customer holds
	 */
	public void accountsHeld() {

		for (Account acc : accounts) {

			System.out.println(acc.getAccountType() + " : " + acc.getBalance());
			acc.status();

		}
		System.out.println("new total balance : " + accounts.get(0).getTotalBalance());
	}

	/**
	 * @param amount invokes the procesCheck() method after setting the CoR using
	 *               setNextHandlers()
	 *
	 */

	public void pay(double amount) {

		if (accounts.get(0).getAccountType().equalsIgnoreCase("checking")) {
			accounts.get(0).setNextHandlers(accounts);
			try {
				payable = checkIfPayable.processCheck((BankAccount) accounts.get(0), amount);
				if (payable)
					System.out.println("\n********************TRANSACTION COMPLETE\n");
				else
					System.out.println("\n********************TRANSACTION FAILED\n");

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public void toggle(String identifier) {
		// Led ON when balance is zero

		sensor = new Led(identifier);
		sensor.toggle();
		System.out.print(identifier + "'s ");
		status();
	}

	/**
	 * print the status of Led sensor
	 */
	public void status() {
		System.out.println("Led Sensor: " + sensor.getStatus());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((checkIfPayable == null) ? 0 : checkIfPayable.hashCode());
		result = prime * result + (payable ? 1231 : 1237);
		result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (checkIfPayable == null) {
			if (other.checkIfPayable != null)
				return false;
		} else if (!checkIfPayable.equals(other.checkIfPayable))
			return false;
		if (payable != other.payable)
			return false;
		if (sensor == null) {
			if (other.sensor != null)
				return false;
		} else if (!sensor.equals(other.sensor))
			return false;
		return true;
	}
}
