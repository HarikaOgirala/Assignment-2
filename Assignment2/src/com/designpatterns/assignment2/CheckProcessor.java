package com.designpatterns.assignment2;
import java.util.concurrent.TimeUnit;

import com.designpatterns.sensors.Led;

public class CheckProcessor {
    String identifier ="CP";
	Boolean payable = false; 
	Account chainAccount;
	Led cpLed=new Led(identifier);
	
	public boolean processCheck(BankAccount checkingAccount, double amount) throws InterruptedException
	{
		try {
			checkingAccount.deduct(amount);	
			payable = true;
		}
		catch(InsufficientFundsException e) {
			
			System.out.println(e.getMessage());
			
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

