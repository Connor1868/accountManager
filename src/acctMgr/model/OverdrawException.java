package acctMgr.model;

import java.math.BigDecimal;

public class OverdrawException  extends Exception{
	
	OverdrawException(BigDecimal balance){
		super("Overdraw by " + balance);
	}
}
