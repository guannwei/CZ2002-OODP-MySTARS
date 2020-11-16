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
import java.util.Scanner;

public class CourseController {
    private HashMap<String,Course> courses;
    private HashMap<Integer, Index> indexes;
    private static FileManager accessFile = new FileManager();
    
    public CourseController( HashMap courses, HashMap indexes ){
        this.courses=courses;
        this.indexes=indexes;
    }
    
    public CourseController() {
    	
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


    public void addCourse(String courseCode, String courseName, String school) throws IOException {

        courses.put(courseCode,new Course(courseCode,courseName,school));
        FileManager.saveCourse(courses);
    }

    public void delCourse(String courseCode) throws IOException {

        courses.remove(courseCode);
        FileManager.saveCourse(courses);


    }

    public int checkVacant(int index){

        if (indexes.get(index)==null){
            return -1;
        }
        int vacancy=indexes.get(index).getVacancy();
        return vacancy;
    }


    
    /* For Students */
    public Boolean checkCourseRegistered(String matric, int index){
    	//Return false if student has not registered for course
    	//Return true if student already registered
    	Boolean exists = false;
    	try {
    		ArrayList<StudentRegisteredCourses> courseList = accessFile.readStudentRegisteredCourses();
    		for(int i = 0; i < courseList.size(); i++) {
    			if(courseList.get(i).getMatricNumber().equals(matric) && courseList.get(i).getIndexNumber() == index) {
    				exists = true;
    			}
    		}	
    	}
    	catch(Exception e) {
    		
    	}
		return exists;
		
    }
    
    public Boolean checkCompleteCourse(String matric, int index){
    	//Return false if student has not completed course
    	//Return true if student already completed course
    	Boolean exists = false;
    	try {
    		ArrayList<StudentRegisteredCourses> courseList = accessFile.readStudentRegisteredCourses();
    		for(int i = 0; i < courseList.size(); i++) {
    			if(courseList.get(i).getMatricNumber().equals(matric) && courseList.get(i).getIndexNumber() == index && courseList.get(i).getComplete() == true) {
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
    
    public Boolean checkVacancy(int index) {
    	Boolean vacant = true;
    	ArrayList<Index> allindexList = new ArrayList<Index>();
    	try {
    		allindexList = accessFile.readIndexArray();
    		for(int i = 0; i < allindexList.size(); i++) {
    			if(allindexList.get(i).getIndexNumber() == index) {
    				if(allindexList.get(i).getVacancy() <= 0) {
    					vacant = false;
    				}
    			}
    		}
    	}
    	catch(Exception e) {
    		
    	}
    	return vacant;
    }

    public Boolean checkClash(String matric, int index) {
    	Boolean clash = false;
    	try {
    		//Get all lesson details
    		ArrayList<Lesson> lessonList = new ArrayList<>();
    		lessonList = accessFile.readLessonArray();
    		
    		ArrayList<Lesson> studentLesson = new ArrayList<>();
    		
    		//Get all student reg courses
    		ArrayList<StudentRegisteredCourses> stuRegCourses = new ArrayList<>();
    		stuRegCourses = accessFile.readStudentRegisteredCourses();
    		
    		//Store all lesson details that student takes
    		for(int i = 0; i < lessonList.size(); i++) {
    			for(int j = 0; j < stuRegCourses.size(); j++) {
    				//If correct matric number
    				if(stuRegCourses.get(j).getMatricNumber() == matric) {
    					if(lessonList.get(i).getIndexNumber() == stuRegCourses.get(j).getIndexNumber()) {
    						studentLesson.add(lessonList.get(i));
    					}
    				}
    			}
    		}
    		
    		//Store details of new index
    		Lesson newIndexLesson = new Lesson();
    		for(int i = 0; i < lessonList.size(); i++) {
    			if(lessonList.get(i).getIndexNumber() == index) {
    				newIndexLesson = lessonList.get(i);
    			}
    		}
    		
    		//Check against all other registered index for student
    		Lesson checkIndexLesson = new Lesson();
    		for(int i = 0; i < studentLesson.size(); i++) {
    			checkIndexLesson = studentLesson.get(i);
    			
    			if(checkIndexLesson.getIndexNumber() != index) {
    				//Check if the lesson details clash
    				
    	    		//If they have same start time, clash
    	    		if(checkIndexLesson.getStartTime().compareTo(newIndexLesson.getStartTime()) == 0) {
    	    			clash = true;
    	    			break;
    	    		}
    	    		//If existing start time is later than new start time & less than new end time, clash
    	    		else if(checkIndexLesson.getStartTime().compareTo(newIndexLesson.getStartTime()) > 0 && checkIndexLesson.getStartTime().compareTo(newIndexLesson.getEndTime()) < 0){
    	    			clash = true;
    	    			break;
    	    		}
    	    		//If new start time is later than existing start time & less than existing end time, clash
    	    		else if(newIndexLesson.getStartTime().compareTo(checkIndexLesson.getStartTime()) > 0 && newIndexLesson.getStartTime().compareTo(checkIndexLesson.getEndTime()) < 0) {
    	    			clash = true;
    	    			break;
    	    		}
    	    		else {
    	    			clash = false;
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
	    	//Get student registered courses
	    	ArrayList<StudentRegisteredCourses> stuRegCourses = new ArrayList<>();
	    	stuRegCourses = accessFile.readStudentRegisteredCourses();
	    	//Get Index 
	    	HashMap<Integer,Index> indexes = new HashMap<>();
	    	indexes = accessFile.readIndex();
	    	
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
	    }
	    catch(Exception e){
	    		
	    }
	}
	
	public void swopIndex(String ownMatric, String peerMatric, int ownIndex, int newIndex) {
		try {
			//Get student registered courses
			ArrayList<StudentRegisteredCourses> stuRegCourses = new ArrayList<>();
	    	stuRegCourses = accessFile.readStudentRegisteredCourses();
	    	
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



