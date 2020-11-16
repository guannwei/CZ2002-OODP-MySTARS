package Boundary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.CourseController;
import Model.*;

public class StudentUI {
	public static void studentMenu(Student student) {
		CourseController courseCtrl = new CourseController();
		
		int choice = 0;
		boolean validInput = false;
		Scanner sc = new Scanner(System.in);
		String matric="";
		
		
		
			
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
				try {
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
		    				System.out.println("List of indexes");
		    				System.out.println("Index	Vacancy");
		    				System.out.println("----------------");
		    				for(int i = 0; i < indexList.size(); i++) {
		    					System.out.println(indexList.get(i).getIndexNumber() + "	" + indexList.get(i).getVacancy());
		    				}
		    				
		    				//Get new index
		    				System.out.println("\nEnter new index");
		    				int newIndex = sc.nextInt();
		    				//Change index if have vacancy
		    				if(courseCtrl.checkVacancy(newIndex) == true) {
		    					courseCtrl.changeIndex(matric, index, newIndex);
		    				}
		    				else {
		    					System.out.println("Index has 0 vacancies, unable to change index!");
		    				}
		    				
		    			}
		    		}
		    	}
		    	catch(Exception e){
		    		
		    	}
				break;
			case 6:

				break;
			case 7:
				System.out.println("Loged out successfully");
				break;
			default:
				System.out.println("");
				break;
			}
		} while (choice > 0 && choice < 7);
		
	}
}
