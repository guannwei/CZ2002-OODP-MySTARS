package Controller;
import java.util.*;
import java.io.*;
import Model.*;
public class AccountController {
	public static String logInUser = "";
	public static String logIn(User[] users, Student[] students) {
		String logInStatus="";
		Console console = System.console();
		String username = console.readLine("Please enter your username: ");
		String password = new String(console.readPassword("Please enter your password: "));
		
		for(int i=0; i < users.length; i++) {
			if (username == users[i].getUsername() && password == users[i].getPassword()) {
				//call accessPeriod
			}else {
				logInStatus = "Invalid username or password";
			}
		}
		return logInStatus; 
	}
	public static void logOut() {
		logInUser = "";
	}
	public static boolean accessPeriod(Date startDate) {
		//check access period
		return false;
	}

}
