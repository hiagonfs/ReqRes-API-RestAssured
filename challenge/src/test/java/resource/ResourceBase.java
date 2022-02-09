package resource;

import static io.restassured.RestAssured.given;

import base.BaseAPI;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ResourceBase extends BaseAPI {

	public static final String BASE_PATH_RESOURCE = "/unknown";
	
	public ResourceBase() {
		
	}
	
	protected Response getListResource() {
		Response response = given()
				.contentType(ContentType.JSON)
				.when()
				.get(BASE_PATH_RESOURCE) 
				.then()
				.extract().response();
		return response;
	}
	
	protected Response getSingleResource(int id) {
		Response response = given()
				.contentType(ContentType.JSON)
				.pathParam("id", id)
				.when()
				.get(BASE_PATH_RESOURCE + "/{id}") 
				.then()
				.extract().response();
		return response;
	}
	
}
