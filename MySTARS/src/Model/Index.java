package Model;

import java.util.List;
import java.util.Queue;

public class Index {
	private int indexNumber;
	private String courseCode;
	private int vacancy;
	private int max;
	private Queue<String> waitList;
	private List<Lesson> listOfLessons;
	
	public Index(int indexNumber, String courseCode, int vacancy, int max, Queue<String> waitList) {
		this.indexNumber = indexNumber;
		this.courseCode = courseCode;
		this.vacancy = vacancy;
		this.max = max;
		this.waitList = waitList;
	}
	
	public Index(int indexNumber, int vacancy, Queue<String> waitList) {
		this.indexNumber = indexNumber;
		this.vacancy = vacancy;
		this.waitList = waitList;
	}
	
	public Index(int indexNumber, String courseCode, int vacancy) {
		this.indexNumber = indexNumber;
		this.courseCode = courseCode;
		this.vacancy = vacancy;
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

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public List<Lesson> getListOfLessons() {
		return listOfLessons;
	}

	public void setListOfLessons(List<Lesson> listOfLessons) {
		this.listOfLessons = listOfLessons;
	}
	

	
	

}
