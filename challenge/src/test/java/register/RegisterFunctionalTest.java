package register;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;

@Epic("Regression test")
@Feature("Register tests")
public class RegisterFunctionalTest extends RegisterBase {
	
	@Test
	@Description("Validate the POST method to create a valid Register")
	void createValidRegister() {
		final int id = 4;
		final String token = "QpwL5tke4Pnpja7X4"; 
		RegisterPOJO register = new RegisterPOJO("eve.holt@reqres.in", "pistol");
		Response response = postRegister(register.getEmail(), register.getPassword());
		final int idResponse = response.getBody().jsonPath().get("id");
		final String tokenResponse = response.getBody().jsonPath().get("token");
		Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
		Assertions.assertEquals(id, idResponse);
		Assertions.assertEquals(token, tokenResponse);
	}
	
	@Test
	@Description("Validate the POST method to create a invalid Register")
	void createInvalidRegisterWithoutPassword() {
		String messageError = "Missing password";
		RegisterPOJO register = new RegisterPOJO("sydney@fife", "");
		Response response = postRegister(register.getEmail(), register.getPassword());
		final String messageErrorResponse = response.getBody().jsonPath().get("error");
		Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode());
		Assertions.assertEquals(messageError, messageErrorResponse);
	}
	


}
