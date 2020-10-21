package Model;

import java.sql.Time;

public class Lesson {
	private int indexNumber;
	private String courseCode;
	private Time startTime;
	private Time endTime;
	private String day;
	private String type;
	private String venue;
	private String staffName;
	
	public Lesson(int indexNumber, String courseCode, Time startTime, Time endTime, String day, String type,
			String venue, String staffName) {
		this.indexNumber = indexNumber;
		this.courseCode = courseCode;
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
		this.type = type;
		this.venue = venue;
		this.staffName = staffName;
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

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	
	
	
	

}
