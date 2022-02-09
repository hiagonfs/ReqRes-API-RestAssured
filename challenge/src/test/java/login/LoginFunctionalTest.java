package login;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;

@Epic("Regression test")
@Feature("Login tests")
public class LoginFunctionalTest extends LoginBase {
	
	@Test
	@Description("Validate the POST method for a valid Login")
	void createValidRegister() {
		final String token = "QpwL5tke4Pnpja7X4"; 
		LoginPOJO register = new LoginPOJO("eve.holt@reqres.in", "cityslicka");
		Response response = postLogin(register.getEmail(), register.getPassword());
		Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
		Assertions.assertEquals(token, response.getBody().jsonPath().get("token"));
	}
	
	@Test
	@Description("Validate the POST method for a invalid Login")
	void createInvalidRegisterWithoutPassword() {
		String messageError = "Missing password";
		LoginPOJO register = new LoginPOJO("peter@klaven", "");
		Response response = postLogin(register.getEmail(), register.getPassword());
		Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode());
		Assertions.assertEquals(messageError, response.getBody().jsonPath().get("error"));
	}

}
