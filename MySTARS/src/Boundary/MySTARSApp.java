package Boundary;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import Controller.AccountController;
import Model.Student;
import Model.Admin;

/**
 * This is the main interface which will be displayed once users starts the application.
 * @author Ray Myat
 *
 */
public class MySTARSApp {
	
	/**
	 * This is the main class which provides the common login interface for the users.
	 * It contains necessary prompts and print statements for the respective operation.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// accountStatus is to track user log
		Admin admin;
		Student student;
		boolean loginStatus = true;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
		System.out.println("------------------------------------------------------------------");
		System.out.println("|		My Student Automated Registration System         |");
		System.out.println("------------------------------------------------------------------");
		while(loginStatus) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String userType = "";
			while (!userType.equals("admin") && !userType.equals("student")) {
				System.out.println("Enter Domain (Admin or Student): ");
				userType = sc.nextLine();
				userType = userType.toLowerCase();
			 }
			if(userType.equals("admin") || userType.equals("student")) {
				System.out.println("Enter username: ");
				String username = sc.nextLine();
				System.out.println("Enter password: ");
				String password = sc.nextLine();
				Object object = AccountController.logIn(userType, username, password);
				if(object != null) {
					if((boolean)object.equals(false)){
						System.out.println("Login Failed - Wrong password");
				    }else {
				    	if(userType.equals("admin")) {
							admin = (Admin)object;
							AdminUI.adminMenu(admin,loginStatus);
						}else {
							student = (Student)object;
							if(!student.checkAccessTime()) {
								System.out.println("Please log in during your registration access period");
								System.out.println("Your registration access period: "+ student.getAccessStartPeriod().format(format)+" to " + student.getAccessEndPeriod().format(format));
							}else {
								StudentUI.studentMenu(student,loginStatus);
							}
						}
				    } 
				}else {
						System.out.println("Login Failed - Please check that you have indicated the correct UserName, Password and Domain info.");
				}
				System.out.println("");
					
			}
		}

	}

}