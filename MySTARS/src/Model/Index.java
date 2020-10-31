package Model;

import java.util.LinkedList;
import java.util.Queue;

public class Index {
	private int indexNumber;
	private String courseCode;
	private int vacancy;
	private Queue<String> waitList;
	
	public Index(int indexNumber, String courseCode, int vacancy, Queue<String> waitList) {
		this.indexNumber = indexNumber;
		this.courseCode = courseCode;
		this.vacancy = vacancy;
		this.waitList = waitList;
	}
	public Index(int indexNumber, String courseCode, int vacancy) {
		this.indexNumber = indexNumber;
		this.courseCode = courseCode;
		this.vacancy = vacancy;
		Queue<String> waitList= new LinkedList<String>();
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
	public Queue<String> getWaitList() {
		return waitList;
	}
	public void setWaitList(Queue<String> waitList) {
		this.waitList = waitList;
	}

	
	

}
