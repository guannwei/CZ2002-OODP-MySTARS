package Boundary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import Controller.CourseController;
import Controller.StudentController;
import Model.*;

/**
 * This is the interface which will be displayed after the student is logged in.
 * @author Guan Wei, Zhi Xuan
 *
 */
public class StudentUI {
	
	/**
	 * This method provides a list of operations that are designated for the student.
	 * It contains necessary prompts and print statements for the respective operation.
	 * @param student Currently logged in student
	 * @param loginStatus Login status of the student.
	 */
	public static void studentMenu(Student student, boolean loginStatus) {
		CourseController courseCtrl = new CourseController();
		StudentController studentCtrl = new StudentController();
		
		int choice = 0;
		boolean validInput = false;
		Scanner sc = new Scanner(System.in);
		String matric= student.getMatricNumber();
		String courseCode;
		int index;
		
		System.out.println("------------------------Student Menu-----------------------");	
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
					System.out.println("Invalid input. Please choose valid option");
					sc.nextLine();
				}
			} while (!validInput);
			validInput = false;

			switch (choice) {
			/*Add course*/
			case 1:
				System.out.println("Enter the course");
				courseCode = sc.nextLine();
				System.out.println("Enter index");
				index = sc.nextInt();
				if(courseCtrl.checkCourse(courseCode)&& courseCtrl.checkIndex(index) == true) {
					if(courseCtrl.checkIndexInCourse(courseCode, index) == true) {
						if(courseCtrl.checkCompleteCourse(matric, index, courseCode) == true) {
			   				System.out.println("You have already completed this course!");
		    			}
						if(courseCtrl.checkTotalAU(student) == true) {
							if(courseCtrl.checkCourseRegistered(matric, index, courseCode) == true) {
					    		System.out.println("You have already registered for this course!");
					   		}else {
					   			if(courseCtrl.checkClash(matric, index, 0)) {
					   				System.out.println("Chosen index clashes with current timetable. Please choose another index");
									
								}else {
				    				if(courseCtrl.checkVacant(index) > 0) {
				    					System.out.println("Succesfully registered!");
				    				}else {
				    					System.out.println("You are added to waitlist!");
				    				}
				    				courseCtrl.registerCourse(student, index, courseCode);
						   		}
					   		}
						}else {
							System.out.println("You have exceeded the total AU!");
						}
					}else {
						System.out.println("Index does not belong under the course. Please re-enter");
					}
				}else if(courseCtrl.checkCourse(courseCode) == false && courseCtrl.checkIndex(index) == false) {
					System.out.println("Invalid index and course code: Please re-enter!");
				}else if(courseCtrl.checkIndex(index) == false) {
					System.out.println("Invalid index: Please re-enter!");
				}else if(courseCtrl.checkCourse(courseCode) == false) {
					System.out.println("Invalid course code: Please re-enter!");
				}
				break;
			/*Drop course*/
			case 2:
				System.out.println("Enter the course");
				courseCode = sc.nextLine();
				System.out.println("Enter index");
				index = sc.nextInt();
				if(courseCtrl.checkCourse(courseCode)&& courseCtrl.checkIndex(index) == true) {
					if(courseCtrl.checkIndexInCourse(courseCode, index) == true) {
						if(courseCtrl.checkCourseRegistered(matric, index,courseCode) == false) {
				    		System.out.println("You have not registered for this course!");
				   		}else {
				   			if(courseCtrl.checkCompleteCourse(matric, index, courseCode) == true) {
				   				System.out.println("You cannot deregister from a course that you have already completed!");
			    			}else {
			    				courseCtrl.deregisterCourse(student, index, courseCode);
			    				System.out.println("You have succesfully de-register from this course!");
			    			}
				   		}
					}else {
						System.out.println("Index does not belong under the course. Please re-enter!");
					}
				}else if(courseCtrl.checkCourse(courseCode) == false && courseCtrl.checkIndex(index) == false) {
					System.out.println("Invalid index and course code: Please re-enter!");
				}else if(courseCtrl.checkIndex(index) == false) {
					System.out.println("Invalid index: Please re-enter!");
				}else if(courseCtrl.checkCourse(courseCode) == false) {
					System.out.println("Invalid course code: Please re-enter!");
				}
				break;
			/*Check/Print courses registered*/
			case 3:
				System.out.println( student.getName() +" registered courses.");
				System.out.println("=============================================================================================");
				HashMap<Integer,Course> regCourses = courseCtrl.getRegisteredCourses(student);
				HashMap<Integer,Course> waitListCourses = courseCtrl.getWaitlistCourses(student);
				if(regCourses.isEmpty()) {
					System.out.println("You have yet to registerd any courses.");
				}else {
					for (Integer i : regCourses.keySet()) {
						System.out.println("Course: " + regCourses.get(i).getCourseCode() + " || Title: " + regCourses.get(i).getCourseName()+" || AU: " + regCourses.get(i).getAu() + " || Index: " + i + " || School: " + regCourses.get(i).getSchool());
						  ArrayList<Lesson> lessons = courseCtrl.getLessons(i);
						  for(Lesson lesson: lessons) {
							  System.out.println(lesson.getType()+"  "+ " Day: " +lesson.getDay() + " Time: " +lesson.getStartTime() + "-" + lesson.getEndTime()+
									  " Venue: " + lesson.getVenue() + " Registered" );
						  }
						  System.out.println("=============================================================================================");
						  
					}
				}
				if(!waitListCourses.equals(null)) {
					for (Integer i : waitListCourses.keySet()) {
						  System.out.println("Course: " + regCourses.get(i).getCourseCode() + " || Title: " + regCourses.get(i).getCourseName()+" || AU: " + regCourses.get(i).getAu() + " || Index: " + i + " || School: " + regCourses.get(i).getSchool());
						  ArrayList<Lesson> lessons = courseCtrl.getLessons(i);
						  for(Lesson lesson: lessons) {
							  //Start end day type venue.
							  System.out.println(lesson.getType()+"  "+ " Day: " +lesson.getDay() + " Time: " +lesson.getStartTime() + "-" + lesson.getEndTime()+
									  " Venue: " + lesson.getVenue() + " On WaitList" );
						  }
						  System.out.println("=============================================================================================");
						  
					}
				}

				break;
			/*Check vacancies available*/
			case 4:
				System.out.println("Enter the course code ");
				courseCode = sc.nextLine();
				ArrayList<Index> courseIndexes = courseCtrl.getIndexInCourse(courseCode);
				if(courseCtrl.checkCourse(courseCode) == true) {
					if(!courseIndexes.isEmpty()) {
						System.out.println("Course Code: " + courseCode);
						System.out.println("Index		Vacancy");
		   				System.out.println("--------------------");
						for(Index indx: courseIndexes) {
							System.out.println(indx.getIndexNumber() + "		" + indx.getVacancy()+"/"+indx.getMax());
						}
					}else {
						System.out.println("Currently, this course code have no indexes.");
					}
				}else {
					System.out.println("Invalid course code: Please re-enter.");
				}
				break;
			/*Change index number of course*/
			case 5:
				System.out.println("Enter the course");
				courseCode = sc.nextLine();
				System.out.println("Enter the original index");
				index = sc.nextInt();
		    	if(courseCtrl.checkCourseRegistered(matric, index, courseCode) == false) {
		    		System.out.println("You have not registered for this course!");
		   		}
		   		else {
		   			if(courseCtrl.checkCompleteCourse(matric, index, courseCode) == true) {
		   				System.out.println("You have alread completed this course!");
	    			}
	    			else {
	    				//Print all indexes that course has
		    			ArrayList<Index> indexList = new ArrayList<Index>();
		    			indexList = courseCtrl.getIndexInCourse(courseCode);
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
		    				if(courseCtrl.checkClash(matric, newIndex, index) == false) {
		    					courseCtrl.changeIndex(matric, index, newIndex, courseCode);
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
			/*Swap index number with another student*/
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
						if(courseCtrl.checkClash(matric, peerIndex, ownIndex) == false) {
							//Check if new index clashes with peer's timetable
							if(courseCtrl.checkClash(peerMatric, ownIndex, peerIndex) == false) {
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
			/*Log out*/
			case 7:
				loginStatus = false;
				System.out.println("Logged out successfully");
				break;
			default:
				System.out.println("Invalid input. Please choose valid option");
				break;
			}
		} while (loginStatus);
		
	}
}
