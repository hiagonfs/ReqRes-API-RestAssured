package register;

import static io.restassured.RestAssured.given;

import base.BaseAPI;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RegisterBase extends BaseAPI {
	
	public static final String BASE_PATH_REGISTER = "/register";
	
	public RegisterBase() {
		
	}
	
	protected Response postRegister(String email, String password) {
		
		String requestBody = "{\n" + "  \"email\": \"" + email + "\",\n" + "  \"password\": \"" + password + "\" \n}";

		Response response = given()
				.contentType(ContentType.JSON)
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when().post(BASE_PATH_REGISTER)
				.then()
				.extract().response();
		return response;
		
	}

}
