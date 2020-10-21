package Model;

import java.util.Queue;

public class Index {
	private int indexNumber;
	private String courseCode;
	private int vacancy;
	private Queue<Student> waitList;
	
	public Index(int indexNumber, String courseCode, int vacancy, Queue<Student> waitList) {
		this.indexNumber = indexNumber;
		this.courseCode = courseCode;
		this.vacancy = vacancy;
		this.waitList = waitList;
	}
	
	public int getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(int indexNumber) {
		this.indexNumber = indexNumber;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public int getVacancy() {
		return vacancy;
	}
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}
	public Queue<Student> getWaitList() {
		return waitList;
	}
	public void setWaitList(Queue<Student> waitList) {
		this.waitList = waitList;
	}

	
	

}
