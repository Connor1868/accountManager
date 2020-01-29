package acctMgr.controller;

import java.math.BigDecimal;

import javax.swing.SwingUtilities;

import acctMgr.model.Account;
import acctMgr.model.OverdrawException;
import acctMgr.view.AccountView;


public class AccountController extends AbstractController {

	public void operation(String opt) {
		if(opt == AccountView.Deposit) {
			//System.out.println("Deposit");
			BigDecimal amount = ((AccountView)getView()).getAmount();
			//System.out.println(amount);
			((Account)getModel()).deposit(amount);
		} else if(opt == AccountView.Withdraw) {
			BigDecimal amount = ((AccountView)getView()).getAmount();
			try {
				((Account)getModel()).withdrawl(amount);
			}
			catch(OverdrawException ex) {
				final String msg = ex.getMessage();
				SwingUtilities.invokeLater(new Runnable() {
				      public void run() {
				    	  ((AccountView)getView()).showError(msg);
				      }
			});
			}
		} 
	}
}

	