package acctMgr.model;

import java.math.BigDecimal;

public class ModelEvent {
	public enum EventKind {
		BalanceUpdate, AgentStatusUpdate, AmountTransferredUpdate
	}
	private EventKind kind;
	private BigDecimal balance;
	public ModelEvent(EventKind kind, BigDecimal transferred){
		this.balance = transferred;
		this.kind = kind;
		//System.out.println("ModelEvent");
	}
	public EventKind getKind(){return kind;}
	public BigDecimal getBalance(){
		return balance;
	}
	
}