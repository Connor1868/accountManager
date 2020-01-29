package acctMgr.test;

import acctMgr.model.Account;
import acctMgr.model.AccountList;
/*
import org.junit.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AccountListTest {
	
	private AccountList accountListTest;
	private Account accountTest;
	
	String idKey = "idKeyTest";
	int size = 1;
	
	@Test
	private void addAccount_Test(){
		
		accountListTest = new AccountList();
		accountTest = new Account();
		accountListTest.addAccount(accountTest);
		int size = accountListTest.getSize();
		assertEquals(size, this.size);
	}
}
*/

import acctMgr.model.Account;
import acctMgr.model.OverdrawException;

import org.junit.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.math.BigDecimal;

/*
 *  Account and AccountList and a combined test class for both of them,
 *	so there should be test classes AccountTest, AccountListTest and CombinedAccountTest
 *
 *  In those test classes there should be testcases for methods deposit, withdraw, add and remove account.
 */



public class AccountListTest {
	
	private AccountList accountListTest;
	private Account accountTest;
	
	String idKey = "idKeyTest";
	int size0 = 0;
	int size1 = 1;
	BigDecimal startBalance = new BigDecimal("0.00");
	
	
	@Test
	public  void addAccount_Test() {
		accountListTest = new AccountList();
		accountTest = new Account(startBalance);
		accountListTest.addAccount(accountTest);
		int size = accountListTest.getSize();
		assertEquals(size, this.size1);
	}
	
	@Test
	public void removeAccount_Test() {
		accountListTest = new AccountList();
		accountTest = new Account(startBalance);
		accountListTest.addAccount(accountTest);
		accountListTest.removeAccount(0);
		int size = accountListTest.getSize();
		assertEquals(size, this.size0);
	}
	
	@Test
	public void getSize_Test() {
		accountListTest = new AccountList();
		accountTest = new Account(startBalance);
		accountListTest.addAccount(accountTest);
		int size = accountListTest.getSize();
		assertEquals(size, this.size1);
	}
	
	@Test
	public void getAccount() {
		accountListTest = new AccountList();
		accountTest = new Account(startBalance);
		accountListTest.addAccount(accountTest);
		assertEquals(accountListTest.getAccount(0), this.accountTest);
	}
	
	/*@Test
	public void write() {

		String file = "/Users/widder/DesktoptestFile.txt";
		
		accountListTest = new AccountList();
		accountTest = new Account();
		accountTest.setID("1");
		accountListTest.addAccount(accountTest);
		accountListTest.write(file);
		accountListTest.read(file);
		//assertEquals();
	
	}*/
	
}
