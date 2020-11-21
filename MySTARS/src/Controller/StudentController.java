package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import Model.Index;
import Model.Student;
import Model.StudentRegisteredCourses;

/***
 * This class has all the methods managing Student object and all objects related to Student
 * @author Guan Wei, Zhi Xuan
 *
 */
public class StudentController {
	private static FileManager accessFile = new FileManager();
	private HashMap<String,Student> studentList = new HashMap<>();
	/***
	 * Constructor reading list of students
	 */
	public StudentController(){
		try {
			studentList = accessFile.readStudents();
		}
		catch(Exception e){
			
		}
	}
	
	/***
	 * This method takes in a matric number and checks whether the matric number exists in student list
	 * @param matric Matric number of student
	 * @return Returns true or false
	 */
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
	
	/***
	 * This method takes in a username and checks whether the username exists in student list
	 * @param username Username of student
	 * @return Returns true or false
	 */
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
	/***
	 * This method takes in a Student object and checks whether the student exists in student list
	 * @param stu Student object
	 * @return Returns true or false
	 */
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
	/***
	 * This method takes in an email and checks whether the email exists in student list
	 * @param email Email of student
	 * @return Returns true or false
	 */
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
	/***
	 * This method takes in username and password and checks whether the password matches the username in student list
	 * @param username Username of student
	 * @param password Password of student
	 * @return Returns true or false
	 */
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
	/***
	 * This method takes in username and returns the matric number matching
	 * @param username Username of student
	 * @return Returns matric number
	 */
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
	/***
	 * This method takes in matric number, start time and end time and edits access period of student
	 * @param matric Matric number of student
	 * @param start Start time for access period
	 * @param end End time for access period
	 */
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
	/***
	 * This method creates a new student object and adds to student list
	 * @param stu Student object
	 */
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
	/***
	 * This method reads student list and returns it
	 * @return Returns a HashMap matric number : Student
	 */
	public HashMap<String, Student> getAllStudent(){
		HashMap<String,Student> alr = new HashMap<>();
		try {
			//Get list of students
			alr = accessFile.readStudents();	
		}
		catch(Exception e) {
		}
		return alr;
	}
	/***
	 * This method takes in an index number and returns an ArrayList of students belonging to that index
	 * @param index Index Number of a course
	 * @return Returns ArrayList of Students
	 */
	public ArrayList<Student> checkStudentsInIndex(int index) {
		ArrayList<Student> studentNominal = new ArrayList<Student>();
		
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
	/***
	 * This method takes in a course code and returns an ArrayList of students taking the course
	 * @param courseCode Course Code of course
	 * @return ArrayList of Students
	 */
	public ArrayList<Student> checkStudentsInCourse(String courseCode) {
		ArrayList studentNominal = new ArrayList<Student>();
		
		try {
			HashMap<Integer,Index> indexList = accessFile.readIndex();
			HashMap<String,Student> studentList = accessFile.readStudents();
			ArrayList<StudentRegisteredCourses> regCourseList = accessFile.readStudentRegisteredCourses();

			for(int i = 0; i<regCourseList.size(); i++) {
				if(indexList.get(regCourseList.get(i).getIndexNumber()).getCourseCode().equals(courseCode)&&!regCourseList.get(i).getComplete()){
					studentNominal.add(studentList.get(regCourseList.get(i).getMatricNumber()));

				}
			}
		}
		catch(Exception e) {
			
		}
		

		return studentNominal;
	}
	
	
	
	
}
