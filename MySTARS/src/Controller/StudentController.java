package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Course;
import Model.Index;
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
	
	public Boolean checkUserNameExists(String username){
		Boolean exists = false;
		try {
			ArrayList<Student> studentList = accessFile.readStudents();
			for(int i = 0; i<studentList.size(); i++) {
				if(studentList.get(i).getUsername().equals(username)) {
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
		catch(Exception e){
		}
	}
	
	public void addStudent(Student stu){
		try {
		//Get list of students
		ArrayList<Student> studentList = accessFile.readStudents();

		//Add student
		Student stuAccess = new Student(stu.getUsername(), stu.getPassword(), stu.getEmail(), stu.getName(), stu.getMatricNumber(), stu.getGender(), stu.getNationality(), LocalDateTime.now(), LocalDateTime.now());
		studentList.add(stuAccess);
		//Write back to file
		accessFile.saveStudent(studentList);
		System.out.println("Student successfully added!");	

		}
		catch(Exception e) {
		
		}
		
	}

	public ArrayList checkStudentsInIndex(int index) throws IOException {
		ArrayList studentNominal = new ArrayList<Student>();

			ArrayList<Student> studentList = accessFile.readStudentsNoAccessTime();
			for(int i = 0; i<studentList.size(); i++) {
				if(studentList.get(i).checkStudentInIndex(index)==Boolean.TRUE){
					studentNominal.add(studentList.get(i));
				}
			}
		return studentList;
	}

	public ArrayList checkStudentsInCourse(String courseName) throws IOException {
		ArrayList studentNominal = new ArrayList<Student>();

		HashMap courseList = FileManager.readCourse();
		ArrayList<Student> studentList = FileManager.readStudentsNoAccessTime();
		Course toFind = (Course) courseList.get(courseName);
		List<Index> indexList = toFind.getIndexList();

		for(int i = 0; i<studentList.size(); i++) {
			for ( int j=0; j<indexList.size(); j++)
			if(studentList.get(i).checkStudentInIndex(indexList.get(j).getIndexNumber())==Boolean.TRUE){
				studentNominal.add(studentList.get(i));
			}
		}
		return studentList;
	}
	
	
	
	
}
