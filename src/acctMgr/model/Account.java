package acctMgr.model;

import java.math.BigDecimal;

import javax.swing.SwingUtilities;

public class Account extends AbstractModel {
	
	private BigDecimal balance = new BigDecimal("0.00");
	private String id;
	private String name;
	
	public Account(BigDecimal balance){
		this.balance = balance;
	}
	
	private BigDecimal balanceZero = new BigDecimal("0.00");

	public String getID() {return id;}
	
	public void setID(String nextIn) {this.id = nextIn;}

	public String getName() {return name;}

	public void setName(String nextIn) {name = nextIn;}

	public BigDecimal getBalance() {return balance;}

	public void deposit(BigDecimal valueOf) {
		System.out.println("Deposit Amount:" + valueOf);
		balance = balance.add(valueOf);
		
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.BalanceUpdate, this.balance);
		SwingUtilities.invokeLater(
			new Runnable() {
			    public void run() {
			    	notifyChanged(me);
			    }
			});
	}
	
	public void withdrawl(BigDecimal valueOf) throws OverdrawException {
		balance = balance.subtract(valueOf);
		if (balance.compareTo(balanceZero) < 0) throw new OverdrawException(balance);
		
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.BalanceUpdate, this.balance);
		SwingUtilities.invokeLater(
			new Runnable() {
			    public void run() {
			    	notifyChanged(me);
			    }
			});
	}
}

	
	