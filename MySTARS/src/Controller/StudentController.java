package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import Model.Course;
import Model.Index;
import Model.Student;
import Model.StudentRegisteredCourses;

public class StudentController {
	private static FileManager accessFile = new FileManager();
	private HashMap<String,Student> studentList = new HashMap<>();
	public StudentController(){
		try {
			studentList = accessFile.readStudents();
		}
		catch(Exception e){
			
		}
	}
	
	public Boolean checkMatricExists(String matric){
		Boolean exists = false;
		try {
			if(studentList.get(matric) != null) {
				exists = true;
			}
		}catch(Exception e) {
			
		}
		return exists;
	}
	
	public Boolean checkUserNameExists(String username){
		Boolean exists = false;
		try {
			//Store all usernames in a list
			List<String> usernames = new ArrayList<String>();
			for(Map.Entry<String, Student> entry : studentList.entrySet()){
				usernames.add(entry.getValue().getUsername());
			}
			
			if(usernames.contains(username)) {
				exists = true;
			}
			
		}catch(Exception e) {
			
		}
		return exists;
	}
	
	public Boolean checkStudentExists(Student stu){
		Boolean exists = false;
		try {
			if(studentList.containsValue(stu)) {
				exists = true;
			}
			
			
		}catch(Exception e) {
			
		}
		return exists;
	}
	
	public Boolean checkEmailExists(String email){
		Boolean exists = false;
		try {
			//Store all emails in a list
			List<String> emails = new ArrayList<String>();
			for(Map.Entry<String, Student> entry : studentList.entrySet()){
				emails.add(entry.getValue().getEmail());
			}
			
			if(emails.contains(email)) {
				exists = true;
			}
		}catch(Exception e) {
			
		}
		return exists;
	}
	
	
	public Boolean checkPassword(String username, String password) {
		Boolean exists = false;
		try {
			for (String i : studentList.keySet()) {
				if(studentList.get(i).hashPassword(password).equals(studentList.get(i).getPassword())) {
					exists = true;
					break;
				}
			}
		}
		catch(Exception e) {
			
		}
		return exists;
	}
	
	public String getMatric(String username){
		String matric = "";
		try {
			for (String i : studentList.keySet()) {
				if(studentList.get(i).getUsername().equals(username)) {
					matric = studentList.get(i).getMatricNumber();
					break;
				}
			}
		}
		catch(Exception e) {
			
		}
		return matric;
	}
	
	
	public void editAccessPeriod(String matric, LocalDateTime start, LocalDateTime end){
		try {
			//Edit student
			if(studentList.get(matric) != null) {
				Student stu = studentList.get(matric);
				stu.setAccessStartPeriod(start);
				stu.setAccessEndPeriod(end);
				studentList.put(matric, stu);
			}
			
			//Write back to file
			accessFile.saveStudent(studentList);
			
		}
		catch(Exception e){
		}
	}
	
	public void addStudent(Student stu){
		try {
			//Get list of students
			HashMap<String,Student> alr = new HashMap<>();
			alr = accessFile.readStudents();	

			stu.setPassword(stu.hashPassword(stu.getPassword()));
			
			//Add student
			alr.put(stu.getMatricNumber(), stu);
			
			//Write back to file
			accessFile.saveStudent(alr);
			
		}
		catch(Exception e) {
		
		}
		
	}

	public ArrayList checkStudentsInIndex(int index) {
		ArrayList studentNominal = new ArrayList<Student>();
		
		try {
			HashMap<String,Student> studentList = accessFile.readStudents();
			ArrayList<StudentRegisteredCourses> regCourseList = accessFile.readStudentRegisteredCourses();

			for(int i = 0; i<regCourseList.size(); i++) {
				if(regCourseList.get(i).getIndexNumber()==index&&!regCourseList.get(i).getComplete()){
					studentNominal.add(studentList.get(regCourseList.get(i).getMatricNumber()));
				}
			}
			if (studentNominal==null){
				return (ArrayList) Collections.<Student>emptyList();
			}
		}
		catch(Exception e){
			
		}
		return studentNominal;
	}

	public ArrayList<Student> checkStudentsInCourse(String courseName) {
		ArrayList studentNominal = new ArrayList<Student>();
		
		try {
			HashMap<Integer,Index> indexList = accessFile.readIndex();
			HashMap<String,Student> studentList = accessFile.readStudents();
			ArrayList<StudentRegisteredCourses> regCourseList = accessFile.readStudentRegisteredCourses();

			for(int i = 0; i<regCourseList.size(); i++) {
				if(indexList.get(regCourseList.get(i).getIndexNumber()).getCourseCode().equals(courseName)&&!regCourseList.get(i).getComplete()){
					studentNominal.add(studentList.get(regCourseList.get(i).getMatricNumber()));

				}
			}
		}
		catch(Exception e) {
			
		}
		

		return studentNominal;
	}
	
	
	
	
}
