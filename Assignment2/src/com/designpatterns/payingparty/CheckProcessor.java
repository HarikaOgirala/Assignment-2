package com.designpatterns.payingparty;
import java.util.concurrent.TimeUnit;

import com.designpatterns.accounts.Account;
import com.designpatterns.exceptions.InsufficientFundsException;
import com.designpatterns.sensors.Led;

public class CheckProcessor {
	String identifier ="CP";
	Boolean payable = false; 
	Led cpLed=new Led(identifier);

	/**
	 * @param checkingAccount
	 * @param amount
	 * @return payable
	 * @throws InterruptedException
	 * 
	 * starting point of CoR deduct functionality
	 */
	public boolean processCheck(Account checkingAccount, double amount) throws InterruptedException
	{
		try {
			checkingAccount.deduct(amount);	
			payable = true;
		}
		catch(InsufficientFundsException e) {

			System.out.println(e);
			payable = false;

			//Led ON and delay it by 1 sec
			cpLed.turnOn();
			System.out.println(cpLed.toString());
			TimeUnit.SECONDS.sleep(1);
			cpLed.toggle();
			System.out.println(cpLed.toString());
		}
		return payable;		
	}
}