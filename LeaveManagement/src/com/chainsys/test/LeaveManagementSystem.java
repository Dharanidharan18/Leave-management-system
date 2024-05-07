package com.chainsys.test;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.chainsys.dao.Employee;


public class LeaveManagementSystem {

		public static void leavemanagement() throws ClassNotFoundException {
			Scanner scanner = new Scanner(System.in);
			
			while (true) {

				System.out.println("1. Apply for Leave ");
				System.out.println("2. Display Generl Holidays");
				System.out.println("3. Exit");
				System.out.print("Enter your choice: ");
				int choice = scanner.nextInt();

				switch (choice) {

				case 1:

					Leave.canGrantLeave();
					scanner.nextLine();
					break;

				case 2:
					System.out.println("Generl Holidays\n");

					GeneralHolidays.printGeneralHolidays();
					break;

				case 3:
					System.out.println("Exiting...");
					scanner.close();
					System.exit(0);
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		}

		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			Scanner scanner = new Scanner(System.in);
			Employee e=new Employee();
			while (true) {
				System.out.println("1. Sign in ");
				System.out.println("2. Admin");
				System.out.println("3. Exit");
				System.out.print("Enter your choice: ");
				int choose = scanner.nextInt();

				switch (choose) {

				case 1:
					System.out.print("Enter employee User Id: ");
					int userId = scanner.nextInt();

					System.out.print("Enter password: ");

					String password = scanner.next();			
					if (e.loginUser(userId, password)) {

						leavemanagement();
					}

					break;

				case 2:
					
					System.out.println("1. Admin Login ");
					
					
					int choie = scanner.nextInt();
					switch(choie) {
					
					case 1:
						
					System.out.print("Enter employee User Id: ");
					userId = scanner.nextInt();

					System.out.print("Enter password: ");

					password = scanner.next();			
					if (e.loginUser(userId, password)) {
						System.out.println("1. Register a New Employee");
						int ch = scanner.nextInt();
						switch(ch) {
						case 1:
							System.out.print("Enter Use");
							
							System.out.print("Enter User ID : ");
							userId = scanner.nextInt();

							System.out.print("Enter Your name : ");
							String name = scanner.next();

							System.out.print("Enter Password : ");
							password = scanner.next();

							if(e.registerUser(userId, name, password)==true) {
								e.insertEmployee(userId,name,password);
							}
							scanner.nextLine();
							break;
						
				
						}

//						leavemanagement();
					}
					
					

				case 3:
					System.out.println("Exiting...");
					scanner.close();
					System.exit(0);
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		}

	}

	class GeneralHolidays {

		public static void printGeneralHolidays() {

			System.out.println("Jan 1 ,Monday New Years Day\r\n" + "Jan 15 ,Monday Pongal\r\n"
					+ "Jan 16 ,Tuesday Thiruvalluvar Day\r\n" + "Jan 17 ,Wednesday Uzhavar Thirunal\r\n"
					+ "Jan 25 ,Thursday Thai Poosam\r\n" + "Jan 26 ,Friday Republic Day\r\n"
					+ "Mar 29 ,Friday Good Friday\r\n"
					+ "Apr 1 ,Monday Annual closing of Accounts for Commercial Banks and Co-operative Banks\r\n"
					+ "Apr 9 ,Tuesday Telugu New Year Day\r\n" + "Apr 11 ,Thursday Ramzan (Idul Fitr)\r\n"
					+ "Apr 14 ,Sunday Tamil New Years Day and Dr.B.R.Ambedkars Birthday\r\n"
					+ "Apr 21 ,Sunday Mahaveer Jayanthi\r\n" + "May 1 ,Wednesday May Day\r\n"
					+ "Jun 17 ,Monday Bakrid(Idul Azha)\r\n" + "Jul 17 ,Wednesday Muharram\r\n"
					+ "Aug 15 ,Thursday Independence Day\r\n" + "Aug 26 ,Monday Krishna Jayanthi\r\n"
					+ "Sep 7 ,Saturday Vinayakar Chathurthi\r\n" + "Sep 16 ,Monday Milad-un-Nabi\r\n"
					+ "Oct 2 ,Wednesday Gandhi Jayanthi\r\n" + "Oct 11 ,Friday Ayutha Pooja\r\n"
					+ "Oct 12 ,Saturday Vijaya Dasami\r\n" + "Oct 31 ,Thursday Deepavali\r\n"
					+ "Dec 25 ,Wednesday Christmas\n");
		}
	}

	class Leave {

		public static void canGrantLeave() throws ClassNotFoundException {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter leave type (SICK LEAVE, CASUAL LEAVE, PERMISSION): ");
			String leaveType = scanner.nextLine().toUpperCase();

			if (leaveType.equals("PERMISSION")) {
				System.out.print("Enter duration (in hours): ");
				int duration = scanner.nextInt();
				if (duration < 0) {
					System.out.println("invalid duration timing");
				} else if (duration > 2) {
					System.out.println("your permission timing exceeds more than 2 hours please re enter the timing ");
					System.out.print("Enter duration (in hours): ");
					duration = scanner.nextInt();
					grantLeave(leaveType, duration);
				}
			} else {
				
				System.out.print("Enter duration of leave (in days): ");
				int duration = scanner.nextInt();
				if (duration < 0) {
					System.out.println("invalid days");
				} 
				
				else if (duration > 2) {
					System.out.println("your leave days exceed more than 2 days please re enter the date ");
					System.out.print("Enter duration (in days): ");
					duration = scanner.nextInt();
					//grantLeave(leaveType, duration);
				}
				
				System.out.print("Enter start date (yyyy-MM-dd): ");
	            Date startDate = getDate();
	            System.out.print("Enter end date (yyyy-MM-dd): ");
	            Date endDate = getDate();
	            
	            while(true) {
	            	
	            if (startDate.after(endDate)) {
	                System.out.println("End date cannot be before start date.");
	                System.out.print("Enter start date (yyyy-MM-dd): ");
		             startDate = getDate();
		            System.out.print("Enter end date (yyyy-MM-dd): ");
		             endDate = getDate();
	            }  else {
	                
	                long diff = endDate.getTime() - startDate.getTime();
	                duration = (int) (diff / (24 * 60 * 60 * 1000)); 
	                Employee employee = new Employee();			
					employee.storeLeaveDetails(leaveType, duration, startDate, endDate);
					grantLeave(leaveType, duration);
					break;
	            }
	            }
	            
				
	            }
			}
		

		
		public static void grantLeave(String leaveType, int duration) {

			System.out.println("\nLeave granted successfully for Employee ID: \n");
		}
	
		
		public static Date getDate() {
			Scanner scanner = new Scanner(System.in);
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false); 

	        Date date = null;
	        while (date == null) {
	            System.out.println("Enter date yyyy-mm-dd:");
	            String dateInput = scanner.next();
	            try {
	                date = dateFormat.parse(dateInput);
	            } catch (ParseException e) {
	                System.out.println("Invalid date format. Please enter date in yyyy-mm-dd format.");
	            }
	        }
	        return date;
	    }
	
		
}
}

	
	