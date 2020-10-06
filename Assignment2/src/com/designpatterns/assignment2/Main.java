/* * Group Members 
 Swetaa Ganesan
 Shobitha Kadandelu Shrinivasa
 Nagaharika Ogirala
 Priyanka Pandey 
 * */
 
package com.designpatterns.assignment2;

public class Main {

	public static void main(String[] args) {
		

		BankAccount checking=new BankAccount("checking",200.0, "CK");
		BankAccount saving=new BankAccount("saving",50.0, "S");
		CreditAccount credit1=new CreditAccount("credit",50.0, "C1");
		CreditAccount credit2=new CreditAccount("credit",50.0, "C2");
		CreditAccount credit3=new CreditAccount("credit",50.0, "C3");
		
		//setting chain
		checking.setNextChain(saving);
		saving.setNextChain(credit1);
		credit1.setNextChain(credit2);
		credit2.setNextChain(credit3);
		
		//create customer object and add accounts to it
		Customer custA = new Customer(checking);
		custA.addAccount(saving);
		custA.addAccount(credit1);
		custA.addAccount(credit2);
		custA.addAccount(credit3);
		
		//print all the accounts of the customer
		System.out.println("***Accounts held by the customer*** ");
		custA.accountsHeld();
		
		//pay()
		System.out.println("****Pay method*****");
		System.out.println("Pay(150)");
		custA.pay(150.0);
		System.out.println("*************");
		System.out.println("Pay(220)");
		custA.pay(220.0);
		System.out.println("*************");
		System.out.println("Pay(5000)");
		custA.pay(5000.0);
		

	}

}
