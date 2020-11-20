package Controller;


import Model.Student;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Admin;

/**
 * @author Ray Myat
 *
 */
public class AccountController {
	
	public static Object logIn(String userType,String userName, String password){
		userType = userType.toLowerCase();
		Object object = new Object();
		object = null;
		try {
			HashMap<String,Student> stuList = FileManager.readStudents();
			@SuppressWarnings("unchecked")
			ArrayList<Admin> adminList = FileManager.readAdmin();
			if(userType.equals("admin")) {
				for(int i = 0; i<adminList.size(); i++) {
					if(userName.equals(adminList.get(i).getUsername())) {
						if(adminList.get(i).hashPassword(password).equals(adminList.get(i).getPassword())) {
							object = adminList.get(i);
						}else {
							object = false;
						}
					}
				}
			}else if(userType.equals("student")){
				for (String i : stuList.keySet()) {
					if(userName.equals(stuList.get(i).getUsername())) {	
						if(stuList.get(i).hashPassword(password).equals(stuList.get(i).getPassword())) {
							object = stuList.get(i);
						}else {
							object = false;
						}
					}
		    	}
				
			}
			
		}catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		return object;
		
	}

}

