package base;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;

public class BaseAPI {
	
	@BeforeAll
	public static void init() {
		RestAssured.baseURI = "https://reqres.in/api";
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

}
