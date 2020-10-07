package com.designpatterns.payingparty;

public class Vendor implements PayingParty {

	public void pay(double amount)
	{
		System.out.println("Vendor made payment");
	}
}
