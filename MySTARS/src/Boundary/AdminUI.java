package Boundary;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.CourseController;
import Controller.FileManager;
import Controller.StudentController;
import Model.*;

public class AdminUI {
	
	public static StudentController stuCtrl = new StudentController();



	public static void adminMenu(Admin admin) throws IOException {
		int choice = 0;
		boolean validInput = false;
		String matric = "";
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = LocalDateTime.now();
		
		Scanner sc = new Scanner(System.in);
		CourseController CCtrl = new CourseController(FileManager.readCourse(),FileManager.readIndex());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		do {
			System.out.println("1. Edit student access period");
			System.out.println("2. Add a student (name, matric number, gender, nationality, etc)");
			System.out.println("3. Add/Update a course (course code, school, its index numbers and vacancy)");
			System.out.println("4. Check available slot for an index number (vacancy in a class)");
			System.out.println("5. Print student list by index number.");
			System.out.println("6. Print student list by course (all students registered for the selected course)");
			System.out.println("7. Logout");

			
			do {
				try {
					choice = sc.nextInt();
				if (choice >= 1) {
					validInput = true;
					sc.nextLine();
					}
				} catch (InputMismatchException e) {
					System.out.println("Enter a valid integer!");
					sc.nextLine();
				}
			} while (!validInput);
			validInput = false;

			switch (choice) {
			case 1:
				validInput = false;
				start = LocalDateTime.now();
				end = LocalDateTime.now();
				do {
					System.out.println("Enter student's matric number: ");
					matric = sc.nextLine();
					if(stuCtrl.checkMatricExists(matric) == true){
						validInput = true;
					}
					else {
						System.out.println("Student does not exist! Please enter a valid matric number!");
					}
				}
				while(!validInput);
				
				
				
				do {
					try {
						System.out.println("Enter start date time for access(YYYY-MM-DD HH:MM): ");
						start = LocalDateTime.parse(sc.nextLine(), formatter);
						validInput = true;
					}
					catch(DateTimeParseException e) {
						System.out.println("Please enter the valid date time format!");
					}
				}while(!validInput);
				
				validInput = false;
				
				do {
					try {
						System.out.println("Enter end date time for access(YYYY-MM-DD HH:MM): ");
						end = LocalDateTime.parse(sc.nextLine(), formatter);
						validInput = true;
					}
					catch(DateTimeParseException e) {
						System.out.println("Please enter the valid date time format!");
					}
					
				}while(!validInput);
				

				stuCtrl.editAccessPeriod(matric, start, end);
				System.out.println("Access period successfully edited!");
				
				break;
			case 2:
				start = LocalDateTime.now();
				end = LocalDateTime.now();
				
				System.out.println("Enter student's matric number: ");
				matric = sc.nextLine();
				System.out.println("Enter student's name: ");
				String name = sc.nextLine();
				System.out.println("Enter student's username: ");
				String username = sc.nextLine();
				System.out.println("Enter student's password: ");
				String password = sc.nextLine();
				System.out.println("Enter student's email: ");
				String email = sc.nextLine();
				System.out.println("Enter student's gender: (Male/Female)");
				String gender = sc.nextLine();
				System.out.println("Enter student's nationality: ");
				String nationality = sc.nextLine();
				
				do {
					try {
						System.out.println("Enter start date time for access(YYYY-MM-DD HH:MM): ");
						start = LocalDateTime.parse(sc.nextLine(), formatter);
						validInput = true;
					}
					catch(DateTimeParseException e) {
						System.out.println("Please enter the valid date time format!");
					}
				}while(!validInput);
				
				validInput = false;
				
				do {
					try {
						System.out.println("Enter end date time for access(YYYY-MM-DD HH:MM): ");
						end = LocalDateTime.parse(sc.nextLine(), formatter);
						validInput = true;
					}
					catch(DateTimeParseException e) {
						System.out.println("Please enter the valid date time format!");
					}
					
				}while(!validInput);
				
				Student stu = new Student(username, password, email, name, matric, gender, nationality, start, end);
				
				//Check if student exists
				if(stuCtrl.checkStudentExists(stu) == true) {
					System.out.println("Student already exists! Please enter fields again!");
				}
				else{
					//Check if matric already exists
					if(stuCtrl.checkMatricExists(matric) == true) {
						System.out.println("Matric number already exists! Please enter fields again!");
					}
					else {
						if(stuCtrl.checkUserNameExists(username) == true) {
							System.out.println("Username already exists! Please enter fields again!");
						}
						else {
							//Check if email exists
							if(stuCtrl.checkEmailExists(email) == true) {
								System.out.println("Email already exists! Please enter fields again!");
							}
							else {
								//Check if gender was entered correctly
								if(gender.equals("Female") || gender.equals("Male")) {
									stuCtrl.addStudent(stu);
									System.out.println("Student successfully added!");	
								}
								else {
									System.out.println("Please enter correct format for gender(Male/Female)! Please enter fields again!");
								}
							}
						}
					}
				}
				

				break;
			case 3:
				CCtrl.updateCourse();

				break;
			case 4:
				CCtrl.checkVacant();

				break;
			case 5:
				System.out.print("Enter Index number: ");
				int indexNum = sc.nextInt();
				//CCtrl.printIndexNomRoll(stuCtrl.checkStudentsInIndex(indexNum),indexNum);

				break;
			case 6:
				System.out.print("Enter Course code: ");
				String courseCode = sc.next();
				//CCtrl.printCourseNomRoll(stuCtrl.checkStudentsInCourse(courseCode),courseCode);

				break;
			case 7:

				break;
			default:
				System.out.println("");
				break;
			}
		} while (choice > 0 && choice < 7);
		
	}
}
