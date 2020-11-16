package Controller;
import java.util.*;

import Model.*;
public class AccountController {
	private static FileManager accessFile = new FileManager();
	
	public static Object logIn(String userType,String userName, String password){
		userType = userType.toLowerCase();
		Object object = new Object();
		object = null;
		try {
			ArrayList<Student> studentList = accessFile.readStudentsArray();
			ArrayList<Admin> adminList = accessFile.readAdmin();
			if(userType.equals("admin")) {
				for(int i = 0; i<adminList.size(); i++) {
					if(userName.equals(adminList.get(i).getUsername()) && adminList.get(i).hashPassword(password).equals(adminList.get(i).getPassword())) {
						object = adminList.get(i);
					}
				}
			}else if(userType.equals("student")){
				for(int i = 0; i<studentList.size(); i++) {
					if(userName.equals(studentList.get(i).getUsername()) && studentList.get(i).hashPassword(password).equals(studentList.get(i).getPassword())) {	
						object = studentList.get(i);
						System.out.println(studentList.get(i).getName());
						System.out.println("in");
					}
				}
				
			}
			
		}catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		return object;
		
	}

	public static void logOut() {
	}

}
