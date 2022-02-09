package login;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginBase {

	public static final String BASE_PATH_LOGIN = "/login";

	public LoginBase() {
	}

	protected Response postLogin(String email, String password) {

		String requestBody = "{\n" + "  \"email\": \"" + email + "\",\n" + "  \"password\": \"" + password + "\" \n}";

		Response response = given()
				.contentType(ContentType.JSON)
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when()
				.post(BASE_PATH_LOGIN)
				.then().extract().response();
		return response;

	}

}
