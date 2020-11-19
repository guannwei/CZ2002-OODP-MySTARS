package Model;

public class StudentRegisteredCourses {
	private String matricNumber;
	private int indexNumber;
	private Boolean complete;
	
	
	public StudentRegisteredCourses(String matricNumber, int indexNumber, Boolean complete) {
		this.matricNumber = matricNumber;
		this.indexNumber = indexNumber;
		this.complete = complete;
	}
	
	
	public String getMatricNumber() {
		return matricNumber;
	}
	public void setMatricNumber(String matricNumber) {
		this.matricNumber = matricNumber;
	}
	public int getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(int indexNumber) {
		this.indexNumber = indexNumber;
	}
	public Boolean getComplete() {
		return complete;
	}
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	
	
}
