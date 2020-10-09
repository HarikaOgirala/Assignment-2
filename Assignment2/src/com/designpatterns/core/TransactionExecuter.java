package com.designpatterns.core;

import com.designpatterns.accounts.BankAccount;
import com.designpatterns.accounts.CreditAccount;
import com.designpatterns.payingparty.Customer;

/**
 * Group Members
 * 
 * @author Swetaa Ganesan 
 *         Nagaharika Ogirala 
 *         Shobitha Kadandelu Shrinivasa
 *         Priyanka Pandey
 **/

public class TransactionExecuter {

	public static void main(String[] args) {

		BankAccount checking = new BankAccount("checking", 200.0, "CK");
		BankAccount saving = new BankAccount("saving", 50.0, "S");
		CreditAccount credit1 = new CreditAccount("credit", 50.0, "C1");
		CreditAccount credit2 = new CreditAccount("credit", 50.0, "C2");
		CreditAccount credit3 = new CreditAccount("credit", 50.0, "C3");

		// create customer object and add accounts to it
		Customer customer = new Customer(checking);
		customer.addAccount(saving);
		customer.addAccount(credit1);
		customer.addAccount(credit2);
		customer.addAccount(credit3);

		// print all the accounts of the customer
		System.out.println("***Accounts held by the customer*** ");
		customer.accountsHeld();

		// pay method call
		System.out.println("----------------------------------------");
		System.out.println("=============Pay(150)===================");
		customer.pay(150.0);
		System.out.println("=========Balances after deduct==========");
		customer.accountsHeld();
		System.out.println("----------------------------------------");
		System.out.println("=============Pay(220)===================");
		customer.pay(220.0);
		System.out.println("=========Balances after deduct==========");
		customer.accountsHeld();
		System.out.println("----------------------------------------");
		System.out.println("=============Pay(5000)==================");
		customer.pay(5000.0);
		System.out.println("=========Balances after deduct==========");
		customer.accountsHeld();

		// deposit new amount to the bankaccount and set new creditlimit and deposit new
		// amount
		checking.makeDeposit(55);
		saving.makeDeposit(75);
		credit1.creditlimit(100);
		credit1.charge(60);
		credit1.charge(10);
		System.out.println("------Adding new values to the accounts-----");
		System.out.println("added new checkings deposit:" + checking.getBalance());
		System.out.println("added new savings deposit:" + saving.getBalance());
		System.out.println("added new credit amount:" + credit1.getBalance());
		System.out.println("-------New BankAccount Balance and CreditAccount Balance-------- ");
		customer.accountsHeld();

	}
}
