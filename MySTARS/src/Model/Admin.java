package Model;

/***
 * Admin class, extends User Class
 * @author Ray
 *
 */
public class Admin extends User{
	public Admin(String username, String password) {
		super(username,password);
	}
	public boolean equals(Object o) {
		if (o instanceof Admin) {
			Admin a = (Admin)o;
			return (getUsername().equals(a.getUsername()));
		}
		return false;
	}
}
