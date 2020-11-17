package Boundary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.CourseController;
import Controller.StudentController;
import Model.*;

public class StudentUI {
	public static void studentMenu(Student student) {
		CourseController courseCtrl = new CourseController();
		StudentController studentCtrl = new StudentController();
		
		int choice = 0;
		boolean validInput = false;
		Scanner sc = new Scanner(System.in);
		String matric= student.getMatricNumber();
		
			
		do {
			System.out.println("1. Add Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Check/Print Courses Registered");
			System.out.println("4. Check Vacancies Available");
			System.out.println("5. Change Index Number of Course");
			System.out.println("6. Swap Index Number With Another Student");
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
				break;
			case 2:
				break;
			case 3:

				break;
			case 4:

				break;
			case 5:
				
				System.out.println("Enter the course");
				String courseCode = sc.nextLine();
				System.out.println("Enter the original index");
				int index = sc.nextInt();
					
		    	if(courseCtrl.checkCourseRegistered(matric, index) == false) {
		    		System.out.println("You have not registered for this course!");
		   		}
		   		else {
		   			if(courseCtrl.checkCompleteCourse(matric, index) == true) {
		   				System.out.println("You have alread completed this course!");
	    			}
	    			else {
	    				//Print all indexes that course has
		    			ArrayList<Index> indexList = new ArrayList<Index>();
		    			indexList = courseCtrl.allIndexOfCourse(courseCode);
		    			System.out.println("List of indexes and vacancies:");
		   				System.out.println("Index		Vacancy");
		   				System.out.println("--------------------");
		   				for(int i = 0; i < indexList.size(); i++) {
		   					System.out.println(indexList.get(i).getIndexNumber() + "		" + indexList.get(i).getVacancy());
	    				}
		   				
		   				//Get new index
		    			System.out.println("\nEnter new index");
		    			int newIndex = sc.nextInt();
		    			//Check if index have vacancy
		    			if(courseCtrl.checkVacant(newIndex) > 0) {
		    				//Check if index clashes
		    				if(courseCtrl.checkClash(matric, index) == false) {
		    					courseCtrl.changeIndex(matric, index, newIndex);
		    					System.out.println("Successfully changed index!");
		    				}
		    				else {
		    					System.out.println("Chosen index clashes with current timetable, unable to change index!");
		    				}
		    				
		    			}
		   				else {
		   					System.out.println("Index has 0 vacancies, unable to change index!");
		   				}	
		   			}
		    	}
		    	
		    	
				break;
			case 6:
				System.out.println("Enter peer's username");
				String peerUsername = sc.nextLine();
				System.out.println("Enter peer's password");
				String peerPassword = sc.nextLine();
				System.out.println("Enter your index");
				int ownIndex = sc.nextInt();
				System.out.println("Enter peer's index");
				int peerIndex = sc.nextInt();
				
				if(studentCtrl.checkUserNameExists(peerUsername) == false) {
					System.out.println("Peer's username does not exist, unable to swap index!");
				}
				else {
					if(studentCtrl.checkPassword(peerUsername, peerPassword) == false) {
						System.out.println("Peer's password is wrong, unable to swap index!");
					}
					else {
						//Get peer matric
						String peerMatric = studentCtrl.getMatric(peerUsername);
						//Check if new index clashes with own timetable
						if(courseCtrl.checkClash(matric, peerIndex) == false) {
							//Check if new index clashes with peer's timetable
							if(courseCtrl.checkClash(peerMatric, ownIndex) == false) {
								courseCtrl.swopIndex(matric, peerMatric, ownIndex, peerIndex);
								System.out.println("Successfully swopped index!");
							}
							else {
								System.out.println("The new index clahses with peer's timetable, unable to swap index!");
							}
						}
						else {
							System.out.println("The new index clashes with current timetable, unable to swap index!");
						}
					}
				}

				break;
			case 7:
				System.out.println("Logged out successfully");
				break;
			default:
				System.out.println("");
				break;
			}
		} while (choice > 0 && choice < 7);
		
	}
}
