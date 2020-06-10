package reservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class reservation {
	InputStreamReader isr = new InputStreamReader(System.in);
	
	BufferedReader br = new BufferedReader(isr);
	
	int pno[] = new int[275];
	String name[] = new String[275];
	String phno[] = new String[275];
	int age[] = new int[275];
	int cl[] = new int[275];
	String time[] = new String[275];
	String train[] = new String[275];
	int count = 0;
	int pnum = 1;
	int max1 = 75;
	int max2 = 125;
	int max3 = 175;
	
	String menuFileName = "\\src\\files\\menuFile.txt";
	String reportFileName = "\\src\\files\\reportFile.txt";
	String projectFilePath = System.getProperty("user.dir");

	public void doMenu()
	{
		int choice = 0;
		System.out.println("\f");
		try
		{
			doHeading();
			
			File menuFile = new File(projectFilePath + menuFileName);
			
			BufferedReader inputFileBufferReader = openFileForRead(menuFile);
			String record = readFileLine(inputFileBufferReader);
			if (record != null && !record.isEmpty()) 
			{
				while (record != null && !record.isEmpty())
				{
					System.out.println(record);
					record = readFileLine(inputFileBufferReader);
				}
			}
			else 
			{
				System.out.println("Menu File is Empty. Exiting from Reservation Program...!!!");
			}
			
			inputFileBufferReader.close();

			System.out.print("Please enter your choice: ");
			choice = Integer.parseInt(br.readLine());
			
			doChoice(choice);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception Generated in doMenu(): " + e.getMessage());
		}
	}
	
	public BufferedReader openFileForRead(File file) 
	{

		BufferedReader bufferedReader = null;
		try
		{
			bufferedReader = new BufferedReader(new FileReader(file));
		}
		catch (IOException e)
		{
			System.out.println("Exception Generated while opening file: " + e.getMessage());
		}
		return bufferedReader;
	}

	public String readFileLine(BufferedReader bufferedReader) 
	{

		String record = null;
		try
		{
			record = bufferedReader.readLine();
		}
		catch (IOException exception) 
		{
			
		}
		return record;
	}

	public void doChoice(int choice) throws Exception 
	{

		 {

			switch (choice) 
			{
			case 1:doBook();
				   break;
				   
			case 2:doCancel();
				   break;
				   
			case 3:doSearch();
				   break;
				   
			case 4:doDispList();
				   break;
				   
			case 5:doDispUnbooked();
				   break;
				   
			case 6:doExit();
				   break;
				   
			default:System.out.println("Invalid choice");
			
			}
			// char e = (char) br.read();
	} while (choice != 6);
	}

	private void doHeading() throws Exception 
	{
		System.out.println("-------------------");
		System.out.println("Railway Reservation");
		System.out.println("-------------------");
		System.out.print("\n");
	}

	private void doBook() throws Exception 
	{
		System.out.println("Please enter the Class of Ticket from below options:");
		System.out.println("1. AC\t 2. First\t 3. Sleeper\t");
		int c = Integer.parseInt(br.readLine());
		
		System.out.println("Please enter the Class of Ticket from below options:");
		System.out.println("1. Chennai Express\t 2. Lakhanau Express\t 3. Santi Express\t");
		int d = Integer.parseInt(br.readLine());
		
		System.out.print("Please enter Number of Tickets to be Booked: ");
		int t = Integer.parseInt(br.readLine());
		
		
		
		int ticketAvailable = 0;
				
		if (c == 1 && max1 >= t) 
		{
			ticketAvailable = 1;
		}
		if (c == 2 && max2 >= t) 
		{
			ticketAvailable = 1;
		}
		if (c == 3 && max3 >= t) 
		{
			ticketAvailable = 1;
		}
		if (d == 1) 
		{
			ticketAvailable = 1;
		}
		if (d == 2) 
		{
			ticketAvailable = 1;
		}
		if (d == 3) 
		{
			ticketAvailable = 1;
		}
		
		
		if (ticketAvailable == 1) 
		{
			for (int i = 0; i < t; i++) 
			{
				pno[count] = pnum;
				
				System.out.print("Please enter Name: ");
				name[count] = br.readLine();
				
				System.out.print("Please enter Age: ");
				age[count] = Integer.parseInt(br.readLine());
				
				cl[count] = c;
				
				//train[count] = d;
				
				System.out.print("Please enter Cell Number: ");
				phno[count] = br.readLine();
				
				System.out.print("Please enter Train Name: ");
				train[count] = br.readLine();
				
				System.out.println("Ticket successfully Booked...!!!");
				
				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		        time[count] = sdf.format(cal.getTime());
				
		        String personData = pno[count] + "|" + name[count].toString() + "|" + age[count] + "|" + phno[count].toString() + "|" + train[count] + "|" + time[count].toString() ;
				
		        doCreateReport(personData);
				
		        count++;
				pnum++;
			}
			if (c == 1) 
			{
				max1 -= t;
				System.out.println("Please pay Rs." + t * 1500);
				doMenu();
			}
			if (c == 2) 
			{
				max2 -= t;
				System.out.println("Please pay Rs." + t * 1200);
				doMenu();
			}
			if (c == 3) 
			{
				max3 -= t;
				System.out.println("Please pay Rs." + t * 1000);
				doMenu();
			}
		}
	}

	public void doCreateReport(String personData) 
	{
		
		File reportFile = new File(projectFilePath, reportFileName);
		writeFileLine(openFileForWrite(reportFile), personData);
	}

	public BufferedWriter openFileForWrite(File reportFile) 
	{
		try {
			if (reportFile.exists()) 
			{
				return new BufferedWriter(new FileWriter(reportFile, true));
			}
			else
			{

				return new BufferedWriter(new FileWriter(reportFile));
			}
		} 
		catch (IOException e)
		{
			System.out.println("Error while Opening File for Writing: " + e.getMessage());
		}
		return null;
	}
	
	public void writeFileLine(BufferedWriter bufferedWriter, String personData) 
	{

		try
		{
			bufferedWriter.write(personData);
			bufferedWriter.newLine();
		}
		catch (IOException e)
		{
			System.out.println("Error while Writing to File: " + e.getMessage());
		}
		finally 
		{
			try
			{
				if (bufferedWriter != null)
					bufferedWriter.close();
			}
			catch (IOException e) 
			{
				System.out.println("Error while Closing File: " + e.getMessage());
			}
		}
	}
	private void doCancel() throws Exception
	{
		int c_pno[] = new int[275];
		String c_name[] = new String[275];
		String c_phno[] = new String[275];
		int c_age[] = new int[275];
		int c_cl[] = new int[275];
		int c_count = 0;
		int passengerFound = 0;
		
		System.out.println("Please enter your passenger no.");
		int p = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < count; i++) 
		{
			if (pno[i] != p) 
			{
				c_pno[c_count] = pno[i];
				c_name[c_count] = name[i];
				c_phno[c_count] = phno[i];
				c_age[c_count] = age[i];
				c_cl[c_count] = cl[i];
				c_count++;
			}
			else
			{
				passengerFound = 1;
				if (cl[i] == 1) 
				{
					max1++;
					System.out.println("Please collect refund of Rs." + 1800);
				}
				if (cl[i] == 2) 
				{
					max2++;
					System.out.println("Please collect refund of Rs." + 1500);
				}
				if (cl[i] == 3) 
				{
					max3++;
					System.out.println("Please collect refund of Rs." + 1000);
				} 
			} 
		}
		if (passengerFound == 1) 
		{
			pno = c_pno;
			name = c_name;
			age = c_age;
			cl = c_cl;
			phno = c_phno;
			count = c_count;
			System.out.println("ticket successfully cancelled");
		}
	}

	private void doDispList() throws Exception 
	{
		System.out.println("Passenger list in AC class");
		System.out.println("pno \t name \t\t age \t phno \t\t train");
		
		for (int i = 0; i < count; i++) 
		{
			if (cl[i] == 1 ) 
			{
				System.out.println(pno[i] + "\t" + name[i] + "\t\t" + age[i] + "\t" + phno[i] + " \t" + train[i]);
				
			}
			
		}
		
		System.out.println("Passenger list in First class");
		System.out.println("pno \t name \t\t age \t phno \t\t train");
		
		for (int i = 0; i < count; i++) 
		{
			if (cl[i] == 2) 
			{
			    System.out.println(pno[i] + "\t" + name[i] + "\t\t" + age[i] + "\t" + phno[i] + " \t" + train[i]);
			    
			}
			
		}
		
		System.out.println("Passenger list in Sleeper class");
		System.out.println("pno \t name \t\t age \t phno \t\t train");
		
		for (int i = 0; i < count; i++) 
		{
			if (cl[i] == 3) 
			{
				System.out.println(pno[i] + "\t" + name[i] + "\t\t" + age[i] + "\t" + phno[i] + " \t" + train[i]);
			    
			}
			
		}
		System.out.println("---------------------------");
		doMenu();
	}

	private void doSearch() throws Exception 
	{
		int passengerFound = 0;
		System.out.println("Please enter passenger no. to search");
		int p = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < count; i++) 
		{
			if (pno[i] == p) 
			{
				System.out.println("Detail found");
				passengerFound = 1;
				System.out.println("passenger no.=" + pno[i]);
				System.out.println("name=" + name[i]);
				System.out.println("class=" + cl[i]);
				System.out.println("phno=" + phno[i]);
				System.out.println("age=" + age[i]);
			}
         doMenu();		
		} 
		if (passengerFound == 0)
		{
			System.out.println("-----------------");
			System.out.println("No such passenger");
			System.out.println("-----------------");
			System.out.println("\n");
		}
			doMenu();
	}

	private void doDispUnbooked() throws Exception 
	{
		System.out.println("No. of booked tickets status");
		System.out.println("AC class" + max1);
		System.out.println("First class" + max2);
		System.out.println("Sleeper class" + max3);
		doMenu();
	}
   
	private void doExit() 
	{
		System.out.println("--------------------------------------------------");
		System.out.println("Thank you for Visit. Exit From Railway Reservation");
		
	}
}
