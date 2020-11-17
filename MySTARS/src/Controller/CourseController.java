package Controller;
import Controller.FileManager;
import Model.Course;
import Model.Index;
import Model.Lesson;
import Model.Student;
import Model.StudentRegisteredCourses;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;

public class CourseController {
    private HashMap<String,Course> courses;
    private HashMap<Integer, Index> indexes;
    private ArrayList<Lesson> lessonList = new ArrayList<>();
    private ArrayList<StudentRegisteredCourses> stuRegCourses = new ArrayList<>();
    
    private static FileManager accessFile = new FileManager();
    
    public CourseController() {
    	try {
    		courses = accessFile.readCourse();
    		indexes = accessFile.readIndex();
    		lessonList = accessFile.readLessonArray();
    		stuRegCourses = accessFile.readStudentRegisteredCourses();
    		
    	}
    	catch(Exception e) {
    		
    	}
    	
    }

    public boolean checkCourse(String courseCode){
		if (courses.get(courseCode)!=null){
			return true;
		}
		else return false;
	}

	public boolean checkIndex(int index){
		if (indexes.get(index)!=null){
			return true;
		}
		else return false;
	}

	public void updateCourseCode(String courseCodeOld, String courseCodeNew){
		Course course =courses.get(courseCodeOld);
		course.setCourseCode(courseCodeNew);
	}

	public void updateCourseSchool(String courseCode, String newSchool){
		Course course =courses.get(courseCode);
		course.setSchool(newSchool);
	}

	public void updateIndex(int newIndex, int oldIndex){
		Index index=indexes.get(newIndex);
		index.setIndexNumber(oldIndex);
	}

	public void updateVacancy(int indexNum, int vacancy){
		Index index=indexes.get(indexNum);
		index.setVacancy(vacancy);
	}

	public void newIndex(int indexNum, String courseCode, int vacancyInt){
    	indexes.put(indexNum,new Index(indexNum,courseCode,vacancyInt));
	}


   //public void updateCourse() throws IOException {
      /*   //scan for Object with courseCode = CourseCode
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Course Code to update:");
        String CourseCode=sc.next();
        if (courses.get(CourseCode)!=null){
            Course course =courses.get(CourseCode);

            System.out.println("Choose what to update: \n" +
                    "1)Course Code\n" +
                    "2)School\n" +
                    "3)Index Numbers\n" +
                    "4)Vacancy\n");

            switch (sc.nextInt()) {
                case 1:
                    System.out.print("Enter new Course Code:");
                    course.setCourseCode(sc.next());
                    System.out.println("Course Code successfully updated!");
                    break;

                case 2:

                    System.out.print("Enter new School:");
                    course.setSchool(sc.next());
                    System.out.println("School successfully updated!");
                    break;

                case 3:
                    System.out.println("Do you want to: \n" +
                            "1)Update an index's number\n" +
                            "2)Add an index\n");

                    switch (sc.nextInt()) {
                        case 1:
                            System.out.print("Enter the index you wish to change: ");
                            String indexChange = sc.next();
                            System.out.print("Enter the new index: ");
                            int newChange = sc.nextInt();
                            indexes.get(indexChange).setIndexNumber(newChange);
                            break;

                        case 2:
                            System.out.print("Enter the index you wish to add: ");
                            int indexNum = sc.nextInt();
                            System.out.print("Enter the Course Code: ");
                            String courseCode = sc.next();
                            System.out.print("Enter the Vacancy: ");
                            int vacancyInt = sc.nextInt();
                            indexes.put(indexNum,new Index(indexNum, courseCode, vacancyInt));
                            break;
                    }
                    break;

                case 4:
                    System.out.print("Enter the index which vacancy you wish to change: ");
                    String indexChange = sc.next();
                    System.out.print("Enter the new vacancy: ");
                    int newChange = sc.nextInt();
                    indexes.get(indexChange).setVacancy(newChange);
                    break;

            }
            FileManager.saveCourse(courses);
            FileManager.saveIndex(indexes);

        }
        else
            System.out.println("Course Code does not exist");





    }
    */


    public void addCourse(String courseCode, String courseName, String school, int au) {
    	try {
    		courses.put(courseCode,new Course(courseCode,courseName,school, au));
            FileManager.saveCourse(courses);
    	}
    	catch(Exception e){
    		
    	}
        
    }

    public void delCourse(String courseCode) {
    	try {
    		courses.remove(courseCode);
            FileManager.saveCourse(courses);
    	}
    	catch(Exception e) {
    		
    	}
    }

    public int checkVacant(int index){
    	int vacancy = 0;
    	if (indexes.get(index)==null){
    		return -1;
        }
    	else {
    		vacancy=indexes.get(index).getVacancy();
    	}
    	
        return vacancy;
    }


    
    public void registerCourse(Student student, int index, String courseCode) {
    	int vacancy = 0;
    	if(indexes != null) {
    		vacancy = indexes.get(index).getVacancy();
        	try {
    			ArrayList<StudentRegisteredCourses> regCourses = accessFile.readStudentRegisteredCourses();
    			if(vacancy > 0) {
        			indexes.get(index).setVacancy(vacancy-1);
    				regCourses.add(new StudentRegisteredCourses(student.getMatricNumber(),index,false));
    				accessFile.saveRegisteredCourses(regCourses);
    			}else {
    				Queue<String> waitList = indexes.get(index).getWaitList();
    				waitList.add(student.getMatricNumber());
    				indexes.get(index).setWaitList(waitList);
    			}
    			accessFile.saveIndex(indexes);
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}else {
    		try {
    			indexes = accessFile.readIndex();
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		System.out.println(indexes);
    	}
    	
    }
    public void deregisterCourse(Student student, int index, String courseCode) {
    	int vacancy = 0;
    	if(indexes != null) {
    		vacancy = indexes.get(index).getVacancy();
    		try {
    			indexes.get(index).setVacancy(vacancy+1);
    			ArrayList<StudentRegisteredCourses> regCourses = accessFile.readStudentRegisteredCourses();
    			for(int i = 0; i<regCourses.size(); i++) {
    				if(regCourses.get(i).getMatricNumber() == student.getMatricNumber() && regCourses.get(i).getIndexNumber() == index) {
    					regCourses.remove(i);
    				}
    			}
    			accessFile.saveIndex(indexes);
    			accessFile.saveRegisteredCourses(regCourses);
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}else {
    		System.out.println(indexes);
    	}
    }
    public Boolean checkCourseRegistered(String matric, int index, String courseCode){
    	//Return false if student has not registered for course
    	//Return true if student already registered
    	Boolean exists = false;
    	try {
    		indexes = accessFile.readIndex();
    		
    		for(int i = 0; i < stuRegCourses.size(); i++) {
    			int ind = stuRegCourses.get(i).getIndexNumber();
    			if(stuRegCourses.get(i).getMatricNumber().equals(matric) && indexes.get(ind).getCourseCode().equals(courseCode)) {
    				System.out.println("Test: " + ind);
    				exists = true;
    			}
    		}	
    	}
    	catch(Exception e) {
    		
    	}
		return exists;
		
    }
    
    public Boolean checkCompleteCourse(String matric, int index, String courseCode){
    	//Return false if student has not completed course
    	//Return true if student already completed course
    	Boolean exists = false;
    	try {
    		ArrayList<StudentRegisteredCourses> courseList = accessFile.readStudentRegisteredCourses();
    		for(int i = 0; i < courseList.size(); i++) {
    			int ind = stuRegCourses.get(i).getIndexNumber();
    			if(courseList.get(i).getMatricNumber().equals(matric) && indexes.get(ind).getCourseCode().equals(courseCode) && courseList.get(i).getComplete() == true) {
    				exists = true;
    			}
    		}
    	}
    	catch(Exception e) {
    		
    	}
		return exists;
		
    }
    
    public ArrayList<Index> allIndexOfCourse(String courseCode) {
    	ArrayList<Index> allindexList = new ArrayList<Index>();
    	ArrayList<Index> indexList = new ArrayList<Index>();
    	try {
    		allindexList = accessFile.readIndexArray();
    		for(int i = 0; i < allindexList.size(); i++) {
    			if(allindexList.get(i).getCourseCode().equals(courseCode)) {
    				indexList.add(allindexList.get(i));
    			}
    		}
    	}
    	catch(Exception e){
    	}	
    	return indexList;
    }

    public Boolean checkClash(String matric, int newIndex, int oldIndex) {
    	Boolean clash = false;
    	try {
    		
    		ArrayList<Lesson> studentLesson = new ArrayList<>();
    		ArrayList<Index> allIndex = new ArrayList<>();
    		
    		//Store all lesson details that student takes
    		for(int i = 0; i < lessonList.size(); i++) {
    			for(int j = 0; j < stuRegCourses.size(); j++) {				
    				//If correct matric number
        			if(stuRegCourses.get(j).getMatricNumber().equals(matric)) {
        				//Get all lesson plans of what student takes
        				if(lessonList.get(i).getIndexNumber() == stuRegCourses.get(j).getIndexNumber()) {
        					studentLesson.add(lessonList.get(i));
        				}
    				}
    				
    			}
    		}
    		
    		//Store details of new index
    		ArrayList<Lesson> newIndexLesson = new ArrayList();
    		for(int i = 0; i < lessonList.size(); i++) {
    			if(lessonList.get(i).getIndexNumber() == newIndex) {
    				newIndexLesson.add(lessonList.get(i));
    			}
    		}
    		
    		//Check against all other registered index for student
    		Lesson checkIndexLesson = new Lesson();
    		outerloop:
    		for(int i = 0; i < studentLesson.size(); i++) {
    			checkIndexLesson = studentLesson.get(i);
    			for(int j = 0; j< newIndexLesson.size(); j++) {
    				Lesson newIndexLes = newIndexLesson.get(j);
    				//If not equals to old index, check if clash
    				if(checkIndexLesson.getIndexNumber() != oldIndex) {
        				//Check if the lesson details clash
        				if(checkIndexLesson.getDay().equals(newIndexLes.getDay())) {
        					//If they have same start time, clash
            	    		if(checkIndexLesson.getStartTime().compareTo(newIndexLes.getStartTime()) == 0) {
            	    			clash = true;
            	    			break outerloop;
            	    		}
            	    		//If existing start time is later than new start time & less than new end time, clash
            	    		else if(checkIndexLesson.getStartTime().compareTo(newIndexLes.getStartTime()) > 0 && checkIndexLesson.getStartTime().compareTo(newIndexLes.getEndTime()) < 0){
            	    			clash = true;
            	    			break outerloop;
            	    		}
            	    		//If new start time is later than existing start time & less than existing end time, clash
            	    		else if(newIndexLes.getStartTime().compareTo(checkIndexLesson.getStartTime()) > 0 && newIndexLes.getStartTime().compareTo(checkIndexLesson.getEndTime()) < 0) {
            	    			clash = true;
            	    			break outerloop;
            	    		}
            	    		else {
            	    			clash = false;
            	    		}
        				}
        				else {
        					clash = false;
        				}
        			}
    			}
    			
    			
    		}

    	}
    	catch(Exception e) {
    		
    	}
    	return clash;
    }

	public void changeIndex(String matric, int index, int newIndex) {
	    try {
	    	
	    	for(int i = 0; i < stuRegCourses.size(); i++) {
	    		//If matric matches
	    		if(stuRegCourses.get(i).getMatricNumber().equals(matric)) {
	    			//If old index matches
	    			if(stuRegCourses.get(i).getIndexNumber() == index) {
	    				//Change new index
		    			stuRegCourses.get(i).setIndexNumber(newIndex);
		    			//Change vacancies
		    			int oldIndexVacancy = indexes.get(index).getVacancy();
		    			indexes.get(index).setVacancy(oldIndexVacancy-1);
		    			int newIndexVacancy = indexes.get(newIndex).getVacancy();
		    			indexes.get(newIndex).setVacancy(newIndexVacancy+1);
	    			}
	    		}
	    	}
	    	//Save back into file
	    	accessFile.saveRegisteredCourses(stuRegCourses);
	    	accessFile.saveIndex(indexes);
	    }
	    catch(Exception e){
	    		
	    }
	}
	
	public void swopIndex(String ownMatric, String peerMatric, int ownIndex, int newIndex) {
		try {
	    	//Swap for own account
	    	for(int i = 0; i < stuRegCourses.size(); i++) {
	    		if(stuRegCourses.get(i).getMatricNumber().equals(ownMatric)) {
	    			//If old index matches
	    			if(stuRegCourses.get(i).getIndexNumber() == ownIndex) {
	    				//Change new index
		    			stuRegCourses.get(i).setIndexNumber(newIndex);
	    			}
	    		}
	    	}
	    	//Swap for peer's account
	    	for(int i = 0; i < stuRegCourses.size(); i++) {
	    		if(stuRegCourses.get(i).getMatricNumber().equals(peerMatric)) {
	    			//If old index matches
	    			if(stuRegCourses.get(i).getIndexNumber() == newIndex) {
	    				//Change new index
		    			stuRegCourses.get(i).setIndexNumber(ownIndex);
	    			}
	    		}
	    	}
	    	
	    	//Save back into file
	    	accessFile.saveRegisteredCourses(stuRegCourses);
		}
		catch(Exception e) {
			
		}
	}


}



