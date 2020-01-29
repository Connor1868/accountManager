package acctMgr.test;

import java.math.BigDecimal;

import acctMgr.model.Account;
import acctMgr.model.OverdrawException;

import org.junit.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/*
 *  Account and AccountList and a combined test class for both of them,
 *	so there should be test classes AccountTest, AccountListTest and CombinedAccountTest
 *
 *  In those test classes there should be testcases for methods deposit, withdraw, add and remove account.
 */



public class AccountTest {
	
	private Account accountTest;
	
	BigDecimal amount = new BigDecimal("1.00");
	BigDecimal amount2, startBalance = new BigDecimal("0.00");
	BigDecimal amount3 = new BigDecimal("2.00");
	BigDecimal overdrawnAmount = new BigDecimal("-1.00");
	String name = "nameTest";
	String id = "idTest";
	
	@Test
	public  void setID_Test() {
		System.out.println("Test Set_ID");
		
		accountTest = new Account(startBalance);
		accountTest.setID(id);
		String id = accountTest.getID();
		//Perform test
		assertEquals(id, this.id);
	}
	
	@Test
	public  void getID_Test(){
		System.out.println("Test Get_ID");
		
		accountTest = new Account(startBalance);
		accountTest.setID(id);
		String id = accountTest.getID();
		//Perform test
		assertEquals(id, this.id);
	}
	
	@Test
	public void setName() {
		System.out.println("Test Set_Name");
		
		accountTest = new Account(startBalance);
		accountTest.setName(name);
		String name = accountTest.getName();
		//Perform test
		assertEquals(name, this.name);	
	}
	
	@Test
	public void getName_Test() {
		System.out.println("Test Get_Name");
		
		accountTest = new Account(startBalance);
		accountTest.setName(name);
		String name = accountTest.getName();
		//Perform test
		assertEquals(name, this.name);
	}
	
	@Test
	public void deposit_Test() {
		System.out.println("Test Deposit");
		
		accountTest = new Account(startBalance);
		accountTest.deposit(this.amount);
		BigDecimal balance = accountTest.getBalance();
		//Perform test
		assertEquals(balance, this.amount);
	}
	
	@Test
	public void getBalance_Test() {
		System.out.println("Test get Balance");
		
		accountTest = new Account(startBalance);
		accountTest.deposit(this.amount);
		BigDecimal balance = accountTest.getBalance();
		//Perform test
		assertEquals(balance, this.amount);	
	}
	
	@Test
	public void withdrawl_Test() throws Exception {
		System.out.println("Test Withdrawl");
		accountTest = new Account(startBalance);
		accountTest.deposit(this.amount);
		accountTest.withdrawl(this.amount);
		BigDecimal amount = accountTest.getBalance();
		//Perform test
		assertEquals(amount.toString(), "0.00");
	}
	
	@Test
	public void overdraw_Test() {
		try {
			accountTest = new Account(startBalance);
			accountTest.deposit(this.amount);
			accountTest.withdrawl(this.amount3);
			
			fail("Overdraw exception should be thrown");
		}
		catch(OverdrawException ex) {
			System.out.println(ex.getMessage());
			assertEquals(ex.getMessage(), "Overdraw by -1.00");
			assertTrue(true);
		}
	
	}
}
