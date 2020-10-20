package Model;

public class Index {
	private int indexNumber;
	private String courseCode;
	private int vacancy;
	private Student[] waitList;
	
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
	public Student[] getWaitList() {
		return waitList;
	}
	public void setWaitList(Student[] waitList) {
		this.waitList = waitList;
	}
	
	

}
