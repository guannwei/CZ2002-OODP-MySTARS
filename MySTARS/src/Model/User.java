package Model;

public class User {
	private String userName;
	private String password;
	private String userType;
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
