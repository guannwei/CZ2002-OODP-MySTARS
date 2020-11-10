package Model;

import java.util.List;

public class Course {
	private String courseCode;
	private String courseName;
	private String school;
	private List<Index> indexList;

	public Course(String courseCode, String courseName, String school) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.school = school;
	}
	
	public Course(String courseCode, String courseName, String school, List<Index> indexList) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.school = school;
		this.indexList = indexList;
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

	public List<Index> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<Index> indexList) {
		this.indexList = indexList;
	}
	
	
	
}
