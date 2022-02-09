package login;

public class LoginPOJO {
	
	private String email;
	private String password;
	
	public LoginPOJO(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

}
