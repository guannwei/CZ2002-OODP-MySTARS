package Controller;

import Model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The class has all the methods managing Course object and all objects related to 
 * @author Ray Myat, Guan Wei, Zhi Xuan
 *
 */
public class CourseController {
	

    private HashMap<String,Course> courses; 
    private HashMap<Integer, Index> indexes;
    private ArrayList<Lesson> lessonList = new ArrayList<>();
    private ArrayList<StudentRegisteredCourses> stuRegCourses = new ArrayList<>();
    private static FileManager accessFile = new FileManager();
    
    /**
     * This is the constructors which load list of courses, indexes, lessonList and studRegCourses respectively.
     */
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

    /**
     * This method takes in course code and checks whether the course code exist in the course list.
     * @param courseCode Course code of course
     * @return Return true or false
     */
    public boolean checkCourse(String courseCode){
		if (courses.get(courseCode) == null){
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * This method takes in index and checks whether the index exists in the index list.
	 * @param index Index number of index
	 * @return Return true or false
	 */
	public boolean checkIndex(int index){
		if (indexes.get(index)!=null){
			return true;
		}
		else return false;
	}
	
	/**
	 * This method takes in course code ,and index and checks whether the index belongs to the course code.
	 * @param courseCode Course code of Course
	 * @param index Index number of index
	 * @return Return true or false
	 */
	public boolean checkIndexInCourse(String courseCode, int index) {
		if(indexes.get(index)!= null) {
			if(indexes.get(index).getCourseCode().equals(courseCode)) {
				return true;
			}else return false;
		}else return false;
	}

	/**
	 * This method takes in old course code , and new course code and updates the old course code to new course code.
	 * @param courseCodeOld Old course code of course
	 * @param courseCodeNew New course code to be updated
	 */
	public void updateCourseCode(String courseCodeOld, String courseCodeNew){
		courses.get(courseCodeOld).setCourseCode(courseCodeNew);
		try {
    		accessFile.saveCourse(courses);
    	}
    	catch(Exception e){
    	}
	}

	/**
	 * This method takes in course code and new school, and updates the school name of the course code to new school name.
	 * @param courseCode Course code of course
	 * @param newSchool New school name to be updated
	 */
	public void updateCourseSchool(String courseCode, String newSchool){
		courses.get(courseCode).setSchool(newSchool);
		try {
    		accessFile.saveCourse(courses);
    	}
    	catch(Exception e){
    	}
		
	}

	/**
	 * This method takes in old index number and new index number, and updates the old index number to new index number.
	 * @param newIndex New index number to be updated
	 * @param oldIndex Old index number of index
	 */
	public void updateIndex(int newIndex, int oldIndex){
		indexes.get(oldIndex).setIndexNumber(newIndex);
		try {
    		accessFile.saveIndex(indexes);
    	}
    	catch(Exception e){
    	}
	}

	/**
	 * This method takes in index number and new vacancy, and updates the vacancy of the index number to new vacancy.
	 * @param indexNum Index number of index
	 * @param newVacancy New vacancy to be updated
	 */
	public void updateVacancy(int indexNum, int newVacancy){
		indexes.get(indexNum).setVacancy(newVacancy);
		try {
    		accessFile.saveIndex(indexes);
    	}
    	catch(Exception e){
    	}
	}

	/**
	 * This method takes in new index number, course code and new vacancy number, and creates a new index under the course code.
	 * @param newIndexNum New index number to be added
	 * @param courseCode Course code of course
	 * @param newVacancyInt Vacancy number of new index number
	 */
	public void newIndex(int newIndexNum, String courseCode, int newVacancyInt){
		Queue<String> waitlist =  new LinkedList<String>();
    	indexes.put(newIndexNum,new Index(newIndexNum,courseCode,newVacancyInt, newVacancyInt, waitlist));
    	try {
    		accessFile.saveIndex(indexes);
    	}
    	catch(Exception e){
    	}
    	
	}

    /**
     * This method takes in course code, course name , school and AU, and creates new course.
     * @param courseCode Course code of new course
     * @param courseName Course name of new course
     * @param school School of the new course
     * @param au AU of the new course
     */
    public void addCourse(String courseCode, String courseName, String school, int au) {
    	try {
    		courses.put(courseCode,new Course(courseCode,courseName,school, au));
    		accessFile.saveCourse(courses);
    	}
    	catch(Exception e){
    		
    	}
        
    }

    /**
     * This method takes in course code and delete the course from courses.
     * @param courseCode Course code of course
     */
    public void delCourse(String courseCode) {
    	try {
    		courses.remove(courseCode);
            accessFile.saveCourse(courses);
    	}
    	catch(Exception e) {
    		
    	}
    }
    
    /**
     * This method load all the courses.
     * @return HashMap Course code : Course
     */
    public HashMap<String,Course> getAllCourses(){
    	HashMap<String,Course> allcourse = new HashMap<>();
    	try {
    		allcourse = accessFile.readCourse();
    		
    	}
    	catch(Exception e) {
    		
    	}
    	return allcourse;
    	
    }

    /**
     * This method takes in index number and checks vacancy of the index number.
     * @param index Index number of index
     * @return vacancy of the index number
     */
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
    
    /**
     * This method create lesson object and adds into lesson list.
     * @param le Lesson object
     */
    public void addLesson(Lesson le) {
    	try {
    		lessonList.add(le);
            accessFile.saveLesson(lessonList);
    	}
    	catch(Exception e){
    		
    	}
        
    }

 
    /**
     * This method takes in student object, index number and course code, and registers student to the course.
     * @param student Student object 
     * @param index Index number of the course to be registered
     * @param courseCode Course code of the course to be registered
     */
    public void registerCourse(Student student, int index, String courseCode) {
       	int vacancy = 0;
    	if(indexes.get(index) != null) {
    		vacancy = indexes.get(index).getVacancy();
        	try {
    			if(vacancy > 0) {
        			indexes.get(index).setVacancy(vacancy-1);
        			stuRegCourses.add(new StudentRegisteredCourses(student.getMatricNumber(),index,false));
    				accessFile.saveRegisteredCourses(stuRegCourses);
    				
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
    	}
    	
    }
    
    /**
     * This method takes in student object, index number and course code, and de-registers student from the course.
     * @param student Student Object
     * @param index Index number of the course to be deregister
     * @param courseCode Course code of the course to be deregister
     */
    public void deregisterCourse(Student student, int index, String courseCode) {
    	int vacancy = 0;
    	if(indexes != null) {
    		vacancy = indexes.get(index).getVacancy();
    		try {
    			HashMap<String,Student> stuList = accessFile.readStudents();
    			for(int i = 0; i<stuRegCourses.size(); i++) {
    				if(stuRegCourses.get(i).getMatricNumber().equals(student.getMatricNumber()) && stuRegCourses.get(i).getIndexNumber() == index) {
    					stuRegCourses.remove(i);
    				}
    			}
    			if(indexes.get(index).getWaitList().size() > 0) {
    				String temp = indexes.get(index).getWaitList().remove();
    				stuRegCourses.add(new StudentRegisteredCourses(temp,index,false));
    				NotificationController.sendNotification(stuList.get(temp), courseCode);
    			}else {
    				indexes.get(index).setVacancy(vacancy+1);
    			}
    			accessFile.saveIndex(indexes);
    			accessFile.saveRegisteredCourses(stuRegCourses);
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
    
    /**
     * This method takes in student object and return list of courses that the student registered.
     * @param student Student object
     * @return List of courses that the student registered 
     */
    public HashMap<Integer,Course> getRegisteredCourses(Student student) {
    	HashMap<Integer,Course> stuCourses = new HashMap<Integer,Course>();
    	
    	for(int i = 0; i <stuRegCourses.size(); i++) {
			if(student.getMatricNumber().equals(stuRegCourses.get(i).getMatricNumber())) {
				int index = stuRegCourses.get(i).getIndexNumber();
				Course course = courses.get(indexes.get(index).getCourseCode());
				stuCourses.put(index,course);
			}
		}
    	return stuCourses;
    	
    }
    
    /**
     * This method takes in student object and return list of courses that the student is on wait list.
     * @param student Student object
     * @return List of courses that the student is on wait list
     */
    public HashMap<Integer,Course> getWaitlistCourses(Student student) {
    	HashMap<Integer,Course> stuWaitListCourses = new HashMap<Integer,Course>();
    	

    	for(Integer i : indexes.keySet()) {
    		if(!indexes.get(i).getWaitList().isEmpty()) {
    			if(indexes.get(i).getWaitList().contains(student.getMatricNumber())){
        			int index = indexes.get(i).getIndexNumber();
        			Course course = courses.get(indexes.get(index).getCourseCode());
        			stuWaitListCourses.put(index,course);
        		}
    		}
    	}
    	return stuWaitListCourses;
    	
    }
    
    /**
     * This method takes in index number and return list of lessons that has the index number.
     * @param index Index number of index
     * @return List of lessons that has the index number
     */
    public ArrayList<Lesson> getLessons(int index) {
    	ArrayList<Lesson> stuLessons = new ArrayList<Lesson>();
    	for(int i=0; i < lessonList.size(); i++) {
    		if(lessonList.get(i).getIndexNumber() == index) {
    			stuLessons.add(lessonList.get(i));
    		}
    	}
    	return stuLessons;
    }
    
    /**
     * This method takes in course code and return list of indexes that has the course code
     * @param courseCode Course code of course
     * @return List of indexes that has the course code
     */
    public ArrayList<Index> getIndexInCourse(String courseCode) {
    	ArrayList<Index> courseIndex = new ArrayList<Index>();
    	for (Integer i : indexes.keySet()) {
    		if(indexes.get(i).getCourseCode().equals(courseCode)) {
    			courseIndex.add(indexes.get(i));
    		}
    	}
    	return courseIndex;
    	
    }
    
    /**
     * This method takes in matric number, index number, and course code and 
     * checks whether the student has registered for the course
     * @param matric Matriculation number of the student
     * @param index Index number of a course
     * @param courseCode Course code of a course
     * @return
     */
    public Boolean checkCourseRegistered(String matric, int index, String courseCode){
    	//Return false if student has not registered for course
    	//Return true if student already registered
    	Boolean exists = false;
    	try {
    		indexes = accessFile.readIndex();
    		
    		for(int i = 0; i < stuRegCourses.size(); i++) {
    			int ind = stuRegCourses.get(i).getIndexNumber();
    			if(stuRegCourses.get(i).getMatricNumber().equals(matric) && indexes.get(ind).getCourseCode().equals(courseCode)) {
    				exists = true;
    			}
    		}	
    	}
    	catch(Exception e) {
    		
    	}
		return exists;
		
    }
    
    /**
     * This method takes in matric number, index number, and course code and checks whether the student has completed the course.
     * @param matric Matriculation number of the student
     * @param index Index number of a course
     * @param courseCode Course code of a course
     * @return
     */
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

    /**
     * This method takes in the matric number of the student, new index number, and old index number 
     * and checks whether the timing of the lessons under the new index number clashed with the lessons under the student's registered indexes.
     * @param matric
     * @param newIndex New index number of the course
     * @param oldIndex Old index number of the course that the student has registered. It can be set to 0 if it is not needed.
     * @return true or false 
     */
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

	/**
	 * This method takes in student's matric number, course code of the course, registered index number of the course, and new index number of the course
	 * and changes the registered index number to the new index number.
	 * @param matric Matriculation number of the student
	 * @param index Registered index number of the course
	 * @param newIndex New index number of the course to be changed
	 * @param courseCode Course code of the course
	 */
	public void changeIndex(String matric, int index, int newIndex, String courseCode) {
	    try {
	    	HashMap<String,Student> stuList = accessFile.readStudents();
	    	for(int i = 0; i < stuRegCourses.size(); i++) {
	    		//If matric matches
	    		if(stuRegCourses.get(i).getMatricNumber().equals(matric)) {
	    			//If old index matches
	    			if(stuRegCourses.get(i).getIndexNumber() == index) {
	    				//Change new index
		    			stuRegCourses.get(i).setIndexNumber(newIndex);
		    			//Check if anyone in waitlist, add them to course if yes
		    			if(indexes.get(index).getWaitList().size() > 0) {
		    				String waitMatric = indexes.get(index).getWaitList().remove();
		    				stuRegCourses.add(new StudentRegisteredCourses(waitMatric,index,false));
		    				NotificationController.sendNotification(stuList.get(waitMatric), courseCode);
		    			}
		    			//Else, just change vacancies
		    			else {
		    				int oldIndexVacancy = indexes.get(index).getVacancy();
			    			indexes.get(index).setVacancy(oldIndexVacancy-1);
			    			int newIndexVacancy = indexes.get(newIndex).getVacancy();
			    			indexes.get(newIndex).setVacancy(newIndexVacancy+1);
		    			}
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
	
	/**
	 * This method takes in student's matric number, peer's matric number, student's index number, and peer's index number 
	 * and swaps peer's and students index.
	 * @param ownMatric Matriculation number of the student
	 * @param peerMatric Matriculation number of the peer
	 * @param ownIndex Index number of the course that the student has already registered 
	 * @param newIndex Index number of the course that the peer has already registered
	 */
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



