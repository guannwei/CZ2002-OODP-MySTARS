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
			HashMap<String,Student> stuList = accessFile.readStudents();
			ArrayList<Admin> adminList = accessFile.readAdmin();
			if(userType.equals("admin")) {
				for(int i = 0; i<adminList.size(); i++) {
					if(userName.equals(adminList.get(i).getUsername()) && adminList.get(i).hashPassword(password).equals(adminList.get(i).getPassword())) {
						object = adminList.get(i);
					}
				}
			}else if(userType.equals("student")){
				for (String i : stuList.keySet()) {
					if(userName.equals(stuList.get(i).getUsername()) && stuList.get(i).hashPassword(password).equals(stuList.get(i).getPassword())) {	
						object = stuList.get(i);
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
