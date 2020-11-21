package Model;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/***
 * Student class with all student details, extends User class
 * @author Ray
 *
 */
public class Student extends User{
	private String email;
	private String name;
	private String matricNumber; 
	private String gender;
	private String nationality;
	private LocalDateTime accessStartPeriod;
	private LocalDateTime accessEndPeriod;
	
	public Student(String username, String password,String email,String name, String matricNumber, String gender, String nationality, LocalDateTime accessStartPeriod, LocalDateTime accessEndPeriod) {
		super(username,password);
		this.email = email;
		this.name = name;
		this.matricNumber = matricNumber;
		this.gender = gender;
		this.nationality = nationality;
		this.accessStartPeriod = accessStartPeriod;
		this.accessEndPeriod = accessEndPeriod;
	}
	
	public Student(String username, String password) {
		super(username, password);
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
	
	public LocalDateTime getAccessStartPeriod() {
		return accessStartPeriod;
	}
	
	public void setAccessStartPeriod(LocalDateTime accessStartPeriod) {
		this.accessStartPeriod = accessStartPeriod;
	}
	
	public LocalDateTime getAccessEndPeriod() {
		return accessEndPeriod;
	}
	
	public void setAccessEndPeriod(LocalDateTime accessEndPeriod) {
		this.accessEndPeriod = accessEndPeriod;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Student) {
			Student s = (Student)o;
			return (getName().equals(s.getUsername()));
		}
		return false;
	}
	
	public boolean checkAccessTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String current = now.format(formatter);
		LocalDateTime currentDT = LocalDateTime.parse(current,formatter);
		if (currentDT.isAfter(accessStartPeriod) && currentDT.isBefore(accessEndPeriod)) {
            return true;
        }
        return false;
		
	}
//	@Override
//	public String toString() {
//        return new StringBuffer(this.getUsername()).append(",").append(super.hashPassword(this.getPassword())).append(",")
//        		.append(this.getName()).append(",").append(this.getMatricNumber()).append(",")
//        		.append(this.getGender()).append(",").append(this.getNationality()).append(",")
//				.append(this.getAccessStartPeriod()).append(",").append(this.getAccessEndPeriod()).toString();
//    }
}