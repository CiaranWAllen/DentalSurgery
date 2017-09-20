import java.io.Serializable;

public class Dentist implements Serializable{
	String password = "password";
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
