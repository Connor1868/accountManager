package acctMgr.view;

import acctMgr.controller.AccountController;
import acctMgr.controller.Controller;
import acctMgr.model.Model;
import acctMgr.model.Account;
import acctMgr.model.ModelEvent;


import java.awt.*; 
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class AccountView extends JFrameView {
	public final static String Deposit = "Deposit";
	public final static String Withdraw = "Withdraw";

	private JPanel topPanel;
	private JPanel textPanel;
	private JPanel buttonPanel;
	private JLabel balanceLabel;
	private JLabel amountLabel;
	private JTextPane balanceField;
	private JTextPane amountField;
	private JButton depButton;
	private JButton withdrawButton;

	Handler handler = new Handler();
	
	public AccountView(Model model, Controller controller){
		super(model, controller);
		this.getContentPane().add(getContent());
		Toolkit toolkit =  Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();
		int x = (int) ((dim.getWidth() - this.getWidth()) * 0.5f);
	    int y = (int) ((dim.getHeight() - this.getHeight()) * 0.5f);
	    this.setLocation(x, y);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent evt) {
		        dispose();
		        System.exit(0);
		    }
		});
		pack();
	}
	public JPanel getContent() {
		if (topPanel == null) {
			topPanel = new JPanel();
			GridLayout layout = new GridLayout(2, 1);
			topPanel.setLayout(layout);
			GridBagConstraints ps = new GridBagConstraints();
			ps.gridx = 0;
			ps.gridy = 0;
			ps.fill = GridBagConstraints.HORIZONTAL;
			
			GridBagConstraints bs = new GridBagConstraints();
			bs.gridx = 0;
			bs.gridy = 1;
			topPanel.add(getTextFieldPanel(), null);
			topPanel.add(getButtonPanel(), null);
		}
		return topPanel;
	}
	public JButton getDepButton(){
			depButton = new JButton(Deposit);
			depButton.addActionListener(handler);
		return depButton;
	}
	public JButton getWithdrawButton(){
			withdrawButton = new JButton(Withdraw);
			withdrawButton.addActionListener(handler);
		return withdrawButton;
	}
	private JPanel getButtonPanel()
	{
		if(buttonPanel == null){
			GridBagConstraints depButtonCtr = new GridBagConstraints();
			depButtonCtr.gridx = 0;
			depButtonCtr.gridy = 0;
			
			GridBagConstraints wButtonCtr = new GridBagConstraints();
			wButtonCtr.gridx = 1;
			wButtonCtr.gridy = 0;
			
			GridBagConstraints depAgButtonCtr = new GridBagConstraints();
			depAgButtonCtr.gridx = 2;
			depAgButtonCtr.gridy = 0;
			
			GridBagConstraints wAgButtonCtr = new GridBagConstraints();
			wAgButtonCtr.gridx = 3;
			wAgButtonCtr.gridy = 0;
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			buttonPanel.add(getDepButton(), depButtonCtr);
			buttonPanel.add(getWithdrawButton(), wButtonCtr);
		}
		
		return buttonPanel;
	}
	private JPanel getTextFieldPanel()
	{
		if(textPanel == null){
			GridBagConstraints bl = new GridBagConstraints();
			bl.gridx = 0;
			bl.gridy = 0;
			
			GridBagConstraints bf = new GridBagConstraints();
			bf.gridx = 1;
			bf.gridy = 0;
			
			GridBagConstraints aml = new GridBagConstraints();
			aml.gridx = 0;
			aml.gridy = 1;
			
			GridBagConstraints amf = new GridBagConstraints();
			amf.gridx = 1;
			amf.gridy = 1;
			
			textPanel = new JPanel();
			textPanel.setLayout(new GridBagLayout());
			textPanel.add(getBalanceLabel(), bl);
			textPanel.add(getBalanceField(), bf);
			textPanel.add(getAmountLabel(), aml);
			textPanel.add(getAmountField(), amf);
			
		}
		return textPanel;
	}
	private JLabel getBalanceLabel(){
		if(balanceLabel == null){
			balanceLabel = new JLabel();
			balanceLabel.setText("Balance:");
			balanceLabel.setPreferredSize(new Dimension(200, 20));
		}
		return balanceLabel;
	}
	
	private JTextPane getBalanceField(){
		if(balanceField == null){
			balanceField = new JTextPane();
			balanceField.setText(((Account)getModel()).getBalance().toString());

			balanceField.setSize(200, 25);
			balanceField.setEditable(false);
		}
		return balanceField;
	}
	private JLabel getAmountLabel(){
		if(amountLabel == null){
			amountLabel = new JLabel();
			amountLabel.setText("Amount:");
			amountLabel.setPreferredSize(new Dimension(200, 20));
		}
		return amountLabel;
	}
	
	private JTextPane getAmountField(){
		if(amountField == null){
			amountField = new JTextPane();
			//Converts Account model balance to BigDecimal for next opperation
			BigDecimal temp = ((Account)getModel()).getBalance().multiply(BigDecimal.valueOf(0.01));	//Multiplys by .01, conversion	**
			amountField.setText(temp.toString());
			amountField.setSize(200, 25);
			amountField.setEditable(true);
		}
		return amountField;
	}

	public BigDecimal getAmount() {
		BigDecimal amount;
		// no checking for parsing errors
		amount = BigDecimal.valueOf(Double.parseDouble(amountField.getText()));
		return amount;
	}
	public void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	public void modelChanged(ModelEvent me){
		System.out.println("Event Occurs");
		if(me.getKind() == ModelEvent.EventKind.BalanceUpdate) {
			System.out.println("Balance field to " + me.getBalance());
			balanceField.setText(me.getBalance().toString());
		}
	}
	
	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((AccountController)getController()).operation(evt.getActionCommand());
		}
	}
	public static void main(String[] args) {
		final Account account = new Account(new BigDecimal("300.00"));
		final AccountController contr = new AccountController();
		contr.setModel(account);
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	    	  AccountView app = new AccountView(account, contr);
	    	  contr.setView(app);
	    	  app.setVisible(true);
	      }
	    });
	  }
}
