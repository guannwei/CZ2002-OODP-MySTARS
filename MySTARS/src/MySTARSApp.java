import java.io.Console;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import Controller.*;
import Boundary.*;
import Model.*;

public class MySTARSApp {
	private static Admin admin;
	private static Student student;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String userType = "";
		while (!userType.equals("admin") && !userType.equals("student")) {
			System.out.println("Enter user type (admin or student)");
			userType = sc.nextLine();
			userType = userType.toLowerCase();
		 }
		if(userType.equals("admin") || userType.equals("student")) {
			System.out.println("Enter your username: ");
			String username = sc.nextLine();
			System.out.println("Enter password: ");
			String password = sc.nextLine();
			if(userType.equals("admin")) {
				admin = (Admin)AccountController.logIn(userType, username, password);
				System.out.println("Loged in");
				System.out.println(admin.getUsername());
				AdminUI.adminMenu(admin);
			}else {
				student = (Student)AccountController.logIn(userType, username, password);
				if(!student.checkAccessTime()) {
					System.out.println("Please log in during your access period");
				}else {
					System.out.println("Loged in");
					System.out.println(student.getName());
					StudentUI.studentMenu(student);
				}
			}
			
		}

	}
	


}
