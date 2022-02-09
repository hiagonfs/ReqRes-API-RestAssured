package register;

public class RegisterPOJO {
	
	private String email;
	private String password;
	
	public RegisterPOJO(String email, String password) {
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
