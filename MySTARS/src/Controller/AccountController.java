package Controller;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Model.*;
public class AccountController {
	private static FileManager accessFile = new FileManager();
	
	public static Object logIn(String userType,String userName, String password){
		userType = userType.toLowerCase();
		Object object = new Object();
		try {
			ArrayList<Student> studentList = accessFile.readStudents();
			ArrayList<Admin> adminList = accessFile.readAdmin();
			if(userType.equals("admin")) {
				for(int i = 0; i<adminList.size(); i++) {
					if(userName.equals(adminList.get(i).getUsername()) && adminList.get(i).hashPassword(password).equals(adminList.get(i).getPassword())) {
						object = adminList.get(i);
					}
				}
			}else if(userType.equals("student")){
				for(int i = 0; i<studentList.size(); i++) {
					if(userName.equals(studentList.get(i).getUsername()) && adminList.get(i).hashPassword(password).equals(studentList.get(i).getPassword())) {	
						object = studentList.get(i);
					}
				}
				
			}else {
				System.out.println("Log in failed");
			}
			
		}catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		return object;
		
	}

	public static void logOut() {
	}

}
