package com.designpatterns.payingparty;

import java.util.concurrent.TimeUnit;

import com.designpatterns.accounts.Account;
import com.designpatterns.exceptions.InsufficientFundsException;
import com.designpatterns.sensors.Led;

public class CheckProcessor {
	public String identifier;
	private Boolean payable = false;
	private Led cpLed;

	/**
	 * @param checkingAccount
	 * @param amount
	 * @return payable
	 * @throws InterruptedException
	 * 
	 *                              starting point of CoR deduct functionality
	 */
	public boolean processCheck(Account checkingAccount, double amount) throws InterruptedException {
		try {
			checkingAccount.deduct(amount);
			payable = true;
		} catch (InsufficientFundsException e) {

			System.out.println(e);
			payable = false;

			identifier = checkingAccount.getIdentifier();
			checkingAccount.status();
			System.out.println("identifier of checking account: " + identifier);
			cpLed = new Led(identifier);
			// Led ON and delay it by 1 sec
			cpLed.turnOn();
			System.out.println(cpLed.toString());
			TimeUnit.SECONDS.sleep(1);
			cpLed.toggle();
			System.out.println(cpLed.toString());
		}
		return payable;
	}
}