package user;

import static io.restassured.RestAssured.given;

import base.BaseAPI;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserBase extends BaseAPI {

	public static final String BASE_PATH_USER = "/users";

	public UserBase() {
	}

	protected Response getUserById(int id) {
		Response response = given()
				.contentType(ContentType.JSON)
				.pathParam("id", id)
				.when()
				.get(BASE_PATH_USER + "/{id}")
				.then()
				.extract().response();
		return response;
	}

	protected Response registerUser(String name, String job) {

		String requestBody = "{\n" + "  \"name\": \"" + name + "\",\n" + "  \"job\": \"" + job + "\" \n}";

		Response response = given()
				.contentType(ContentType.JSON)
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.when().post(BASE_PATH_USER)
				.then()
				.extract().response();
		return response;

	}
	
	protected Response getListUsers(int page) {
		Response response = given()
				.contentType(ContentType.JSON)
				.when()
				.get(BASE_PATH_USER + "?page="+page) 
				.then()
				.extract().response();
		return response;
	}
	
	protected Response getlistUsersDelay(int delay) {
		Response response = given()
				.contentType(ContentType.JSON)
				.when()
				.get(BASE_PATH_USER + "?delay="+ delay) 
				.then()
				.extract()
				.response();
		return response;
	}
	
	protected Response patchUser(String name, String job, int id) {
		String requestBody = "{\n" + "  \"name\": \"" + name + "\",\n" + "  \"job\": \"" + job + "\" \n}";

		Response response = given()
				.contentType(ContentType.JSON)
				.header("Content-type", "application/json")
				.pathParam("id", id)
				.body(requestBody)
				.when().patch(BASE_PATH_USER + "/{id}")
				.then()
				.extract().response();
		return response;
	}
	
	protected Response putUser(String name, String job, int id) {
		String requestBody = "{\n" + "  \"name\": \"" + name + "\",\n" + "  \"job\": \"" + job + "\" \n}";

		Response response = given()
				.contentType(ContentType.JSON)
				.header("Content-type", "application/json")
				.pathParam("id", id)
				.and()
				.body(requestBody)
				.when().put(BASE_PATH_USER + "/{id}")
				.then()
				.extract().response();
		return response;
	}
	
	protected Response deleteUser(int id) {

		Response response = given()
				.contentType(ContentType.JSON)
				.header("Content-type", "application/json")
				.pathParam("id", id)
				.and()
				.when().delete(BASE_PATH_USER + "/{id}")
				.then()
				.extract().response();
		return response;
	}
	
}
