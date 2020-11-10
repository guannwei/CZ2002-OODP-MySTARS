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
	
	public static Boolean checkStudentExists(String matric) throws IOException {
		Boolean exists = false;
		ArrayList<Student> studentList = accessFile.readStudents();
		for(int i = 0; i<studentList.size(); i++) {
			if(studentList.get(i).getMatricNumber() == matric) {
				exists = true;
				break;
			}	
		}
		return exists;
	}
	
	
	public static void editAccessPeriod(String matric, LocalDateTime start, LocalDateTime end) throws IOException {
		//Get list of students
		ArrayList<Student> studentList = accessFile.readStudents();
		//Check if student exists
		if(checkStudentExists(matric) == true) {
			//Edit student
			for(int i = 0; i<studentList.size(); i++) {
				if(studentList.get(i).getMatricNumber() == matric) {
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
	
	public static void addStudent(Student stu) throws IOException {
		//Get list of students
		ArrayList<Student> studentList = accessFile.readStudents();
			//Check if student exists
			if(checkStudentExists(stu.getMatricNumber()) == false) {
				//Add student
				studentList.add(stu);
				
				//Write back to file
				accessFile.saveStudent(studentList);
				System.out.println("Student successfully added!");
			}
			else {
				System.out.println("Student already exists!");
			}
		
	}
	
	
	
	
}
