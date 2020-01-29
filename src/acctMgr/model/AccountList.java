
package acctMgr.model;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;




public class AccountList extends AbstractModel {
	
	private ArrayList<Account> list;
	private int listLength;
	
	
	public AccountList()
	{
		list = new ArrayList<Account>();
		listLength = 0;
	}
	
	
	public void addAccount(Account acc)
	{
		list.add(acc);
		listLength = list.size();
		//notifyChanged(ModelEvent);
	}
	
	
	public void removeAccount(int index)
	{
		if(index >= 0 && index < listLength)
		{
			list.remove(index);
			listLength = list.size();
			//notifyChanged(ModelEvent);
		}
	}
	
	
	public int getSize()
	{
		return listLength;
	}
	
	
	public Account getAccount(int index)
	{
		return list.get(index);
	}
	
	
	public Account getAccount(String idKey) throws NoSuchElementException
	{
		//System.out.println("searching for account with ID = " + idKey);
		Account result = null;
		for(int i = 0; i < listLength; ++i)
		{
			//System.out.println("i : " + i + "Account(" + i + ").ID = " + list.get(i).getID());
			if(list.get(i).getID().compareTo(idKey) == 0)
			{
				result = getAccount(i); 
				//System.out.println("Found it!"); 
				i = listLength + 1; 
			}
		}
		if(result != null) { return result;}
		else {throw new NoSuchElementException("No account exists with ID " + idKey);}
	}
	
	
	public void write(String filename)
	{
		//what if account list is empty when write is called?
		//could call an exception, currently just an if statement
		//if list is empty, write does nothing
		if(listLength >  0)
		{
			FileOutputStream fileStream = null;
			BufferedWriter bw = null;
			try
			{
				fileStream = new FileOutputStream(filename);
				bw = new BufferedWriter(new OutputStreamWriter(fileStream));
				
			}
			catch(IOException err)
			{
				System.out.println("Error creating file output stream: " + err.getMessage());
			}
			//write number of accounts to file
			try
			{ 
				bw.write(Integer.toString(listLength)); bw.newLine(); 
			}
			catch(IOException err) {System.out.println(err.getMessage()); }
			
			//for each element in list
			for(int i = 0; i < listLength; ++i)
			{
				try
				{
					//write each member of the current element to filename
					//write curr_ele.getID()
					bw.write(list.get(i).getID());
					bw.newLine();
					//write curr_ele.getName()
					bw.write(list.get(i).getName());
					bw.newLine();
					//write curr_ele.getBalance()
					bw.write(list.get(i).getBalance().toPlainString());
					bw.newLine();
				}
				catch(IOException err)
				{
					System.out.println("Error writing to file " + filename + " : " + err.getMessage());
				}
			}
			
			try{ bw.close(); fileStream.close(); }
			catch(IOException err) 
			{ System.out.println("Error closing file " + filename +" : " + err.getMessage()); }
			
		}
	}
	
	
	public void read(String filename)
	{
		File file = new File (filename);
		Scanner scanner = null;
		try 
		{
			scanner = new Scanner(file);
		}
		catch(FileNotFoundException err)
		{
			System.out.println("Error: " + err.getMessage());
		}
		
		//scanner.useDelimiter("\r\n"); //using default delimiter
		//if list isn't empty, clear list first
		if(listLength > 0)
		{
			for(int i = listLength - 1; i < -1; --i)
			{
				this.removeAccount(i);
			}
		}
		
		//read number of accounts
		listLength = scanner.nextInt();
		//System.out.println(listLength);
		scanner.nextLine();
		//reads an empty line w/ unknown blank char for some reason 
		//calling nextLine() once skips the problem line
		//should try to find a better fix later if time permits
		
		int i = 0;
		while(scanner.hasNext())
		{
			Account temp = new Account(null);
			
			String nextIn = null;
			//read ID
			nextIn = scanner.nextLine();
			temp.setID(nextIn);
			//System.out.println("[" + i + "] ID: " + nextIn);
			
			//read name
			nextIn = scanner.nextLine();
			temp.setName(nextIn);
			//System.out.println("[" + i + "] Name: " + nextIn);
			
			//read balance
			nextIn = scanner.nextLine();
			temp.deposit(BigDecimal.valueOf(Double.parseDouble(nextIn)));
			//System.out.println("[" + i + "] Balance: " + nextIn);	
			
			//list.add(temp);
			addAccount(temp);
			++i;
		}
		
		scanner.close();
		//notfyChanged(ModelEvent);
	}
	
}
