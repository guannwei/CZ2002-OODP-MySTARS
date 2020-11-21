package Model;

import java.time.LocalTime;
/***
 * Lesson class with all lesson details
 * @author Guan Wei
 *
 */
public class Lesson {
	private int indexNumber;
	private LocalTime startTime;
	private LocalTime endTime;
	private String day;
	private String type;
	private String venue;

	
	public Lesson(int indexNumber, LocalTime startTime, LocalTime endTime, String day, String type,
			String venue) {
		this.indexNumber = indexNumber;
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
		this.type = type;
		this.venue = venue;

	}
	public Lesson() {
		
	}

	public int getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(int indexNumber) {
		this.indexNumber = indexNumber;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
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
	
	

}
