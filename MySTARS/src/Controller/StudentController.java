package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.*;

import Model.Course;
import Model.Index;
import Model.Student;

public class StudentController {
	private static FileManager accessFile = new FileManager();
	
	public Boolean checkMatricExists(String matric){
		Boolean exists = false;
		try {
			HashMap<String,Student> alr = new HashMap<>();
			alr = accessFile.readStudents();
			if(alr.get(matric) != null) {
				exists = true;
			}
		}catch(Exception e) {
			
		}
		return exists;
	}
	
	public Boolean checkUserNameExists(String username){
		Boolean exists = false;
		try {
			HashMap<String,Student> alr = new HashMap<>();
			alr = accessFile.readStudents();
			
			//Store all usernames in a list
			List<String> usernames = new ArrayList<String>();
			for(Map.Entry<String, Student> entry : alr.entrySet()){
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
			HashMap<String,Student> alr = new HashMap<>();
			alr = accessFile.readStudents();
			if(alr.containsValue(stu)) {
				exists = true;
			}
			
			
		}catch(Exception e) {
			
		}
		return exists;
	}
	
	public Boolean checkEmailExists(String email){
		Boolean exists = false;
		try {
			HashMap<String,Student> alr = new HashMap<>();
			alr = accessFile.readStudents();
			
			//Store all emails in a list
			List<String> emails = new ArrayList<String>();
			for(Map.Entry<String, Student> entry : alr.entrySet()){
				emails.add(entry.getValue().getEmail());
			}
			
			if(emails.contains(email)) {
				exists = true;
			}
		}catch(Exception e) {
			
		}
		return exists;
	}
	
	public Boolean checkPassword(String password) {
		Boolean exists = false;
		try {
			ArrayList<Student> studentList = new ArrayList<Student>();
			studentList = accessFile.readStudentsArray();
			
			for(int i = 0; i<studentList.size(); i++) {
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
			ArrayList<Student> studentList = new ArrayList<Student>();
			studentList = accessFile.readStudentsArray();
			
			for(int i = 0; i<studentList.size(); i++) {
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
			//Get list of students
			HashMap<String,Student> alr = new HashMap<>();
			alr = accessFile.readStudents();
			
			//Edit student
			if(alr.get(matric) != null) {
				Student stu = alr.get(matric);
				stu.setAccessStartPeriod(start);
				stu.setAccessEndPeriod(end);
				alr.put(matric, stu);
			}
			
			//Write back to file
			accessFile.saveStudent(alr);
			
		}
		catch(Exception e){
		}
	}
	
	public void addStudent(Student stu){
		try {
			//Get list of students
			HashMap<String,Student> alr = new HashMap<>();
			alr = accessFile.readStudents();	
			
			//Add student
			Student stuAccess = new Student(stu.getUsername(), stu.getPassword(), stu.getEmail(), stu.getName(), stu.getMatricNumber(), stu.getGender(), stu.getNationality(), LocalDateTime.now(), LocalDateTime.now());	
			alr.put(stuAccess.getMatricNumber(), stuAccess);
			
			//Write back to file
			accessFile.saveStudent(alr);
			
		}
		catch(Exception e) {
		
		}
		
	}

	public ArrayList checkStudentsInIndex(int index) throws IOException {
		ArrayList studentNominal = new ArrayList<Student>();

			ArrayList<Student> studentList = new ArrayList();
			studentList= accessFile.readStudentsArray();

			for(int i = 0; i<studentList.size(); i++) {
				if(studentList.get(i).checkStudentInIndex(index)==true){
					studentNominal.add(studentList.get(i));
				}
			}
		if (studentNominal==null){
			return (ArrayList) Collections.<Student>emptyList();
		}
		return studentNominal;
	}

	public ArrayList checkStudentsInCourse(String courseName) throws IOException {
		ArrayList studentNominal = new ArrayList<Student>();

		HashMap courseList = FileManager.readCourse();
		ArrayList<Student> studentList = FileManager.readStudentsArray();
		Course toFind = (Course) courseList.get(courseName);
		List<Index> indexList = toFind.getIndexList();

		for(int i = 0; i<studentList.size(); i++) {
			for ( int j=0; j<indexList.size(); j++)
			if(studentList.get(i).checkStudentInIndex(indexList.get(j).getIndexNumber())==true){
				studentNominal.add(studentList.get(i));
			}
		}
		if (studentNominal==null){
			return (ArrayList) Collections.<Student>emptyList();
		}
		return studentList;
	}
	
	
	
	
}
