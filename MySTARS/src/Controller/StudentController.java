package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Model.Student;

public class StudentController {
	private static FileManager accessFile = new FileManager();
	
	public Boolean checkMatricExists(String matric){
		Boolean exists = false;
		try {
			ArrayList<Student> studentList = accessFile.readStudents();
			for(int i = 0; i<studentList.size(); i++) {
				if(studentList.get(i).getMatricNumber().equals(matric)) {
					exists = true;
					break;
				}	
			}
		}catch(Exception e) {
			
		}
		return exists;
	}
	
	public Boolean checkStudentExists(Student stu){
		Boolean exists = false;
		try {
			ArrayList<Student> studentList = accessFile.readStudentsNoAccessTime();
			for(int i = 0; i<studentList.size(); i++) {
				if(studentList.get(i) == stu) {
					exists = true;
					break;
				}	
			}
		}catch(Exception e) {
			
		}
		return exists;
	}
	
	public Boolean checkEmailExists(String email){
		Boolean exists = false;
		try {
			ArrayList<Student> studentList = accessFile.readStudentsNoAccessTime();
			for(int i = 0; i<studentList.size(); i++) {
				if(studentList.get(i).getEmail().equals(email)) {
					exists = true;
					break;
				}	
			}
		}catch(Exception e) {
			
		}
		return exists;
	}
	
	
	public void editAccessPeriod(String matric, LocalDateTime start, LocalDateTime end){
		try {
			//Get list of students
			ArrayList<Student> studentList = accessFile.readStudents();
			//Check if student exists
			if(checkMatricExists(matric) == true) {
				//Edit student
				for(int i = 0; i<studentList.size(); i++) {
					if(studentList.get(i).getMatricNumber().equals(matric)) {
						studentList.get(i).setAccessStartPeriod(start);
						studentList.get(i).setAccessEndPeriod(end);
						break;
					}
				}
				//Write back to file
				accessFile.saveStudent(studentList);
				System.out.println("Access period successfully edited!");
			}
			else {
				System.out.println("Student does not exist!");
			}
			
		}
		catch(Exception e){
			
		}
		
	}
	
	public void addStudent(Student stu){
		try {
		//Get list of students
		ArrayList<Student> studentList = accessFile.readStudents();
		if(stu.getMatricNumber().length() >= 8) {
			if(checkStudentExists(stu) == false) {
				if(checkMatricExists(stu.getMatricNumber()) == false) {
					if(checkEmailExists(stu.getEmail()) == false) {
						//Add student
						studentList.add(stu);	
						//Write back to file
						accessFile.saveStudent(studentList);
						System.out.println("Student successfully added!");	
					}
					else {
						System.out.println("Email already exists! Please enter fields again!");
					}
					
				}
				else {
					System.out.println("Matric number already exists! Please enter fields again!");
				}
				
			}
			else {
				System.out.println("Student already exists! Please enter fields again!");
			}
			
		}
		else {
			System.out.println("Matric number is too short. It needs to be at least 7 letters. Please enter fields again!");
		}
		
			
		
		}
		catch(Exception e) {
		
		}
		
	}
	
	
	
	
}
