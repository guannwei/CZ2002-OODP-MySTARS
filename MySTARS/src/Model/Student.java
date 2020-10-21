package Model;

public class Student {
	private String name;
	private String matricNumber; 
	private String gender;
	private String nationality;
	
	public Student(String name, String matricNumber, String gender, String nationality) {
		this.name = name;
		this.matricNumber = matricNumber;
		this.gender = gender;
		this.nationality = nationality;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMatricNumber() {
		return matricNumber;
	}
	public void setMatricNumber(String matricNumber) {
		this.matricNumber = matricNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	


}
