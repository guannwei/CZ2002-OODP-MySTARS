package Model;

import java.util.ArrayList;
import java.util.List;

/***
 * Course class with all course details
 * Has list of Index classes
 * @author Guan Wei
 *
 */
public class Course {
	private String courseCode;
	private String courseName;
	private String school;
	private int au;
	private List<Index> listOfIndexes;

	public Course(String courseCode, String courseName, String school, int au) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.school = school;
		this.au = au;
	}
	
	public Course(String courseCode, String courseName, String school, int au, List<Index> listOfIndexes) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.school = school;
		this.au = au;
		this.listOfIndexes = listOfIndexes;
	}
		
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}

	public int getAu() {
		return au;
	}

	public void setAu(int au) {
		this.au = au;
	}

	public List<Index> getListOfIndexes() {
		return listOfIndexes;
	}

	public void setListOfIndexes(List<Index> listOfIndexes) {
		this.listOfIndexes = listOfIndexes;
	}
	
	
	
	
	
}
