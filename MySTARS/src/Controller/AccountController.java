package Controller;


import Model.Student;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Admin;

/**
 * This class has a method that handle all type of users login to the system.
 * @author Ray Myat
 *
 */
public class AccountController {
	
	/**
	 * This method take in usertype, username and password and return relevant object.
	 * On successful log in as student, it will return student object.
	 * On successful log in as admin, it will return admin object.
	 * If the user entered wrong password, this method will return false.
	 * If the return object is null, the user entered wrong username and password.
	 * @param userType Domain of the user
	 * @param userName Username of the student/admin
	 * @param password Password of the student/admin
	 * @return object (boolean or admin or student)
	 */
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

