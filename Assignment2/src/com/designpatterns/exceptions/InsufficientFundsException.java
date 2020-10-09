package com.designpatterns.exceptions;

/**
 * throws: bankaccount.deduct(), creditaccount.deduct() handled in
 * CheckProcessor
 *
 */
public class InsufficientFundsException extends RuntimeException {

	private static final long serialVersionUID = 2417948091674977171L;

	public String toString() {
		return "You have insufficient funds in your accounts";
	}
}
