package user;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;

@Epic("Regression test")
@Feature("User tests")
public class UserFunctionalTest extends UserBase {

	@Test
	@Description("Validate GET method to get list of Users per page")
	void validateGetListOfUsersPaged() {
		int page = 2;
		int idUser = 7;
		int positionInList = 0; 
		Response response = getListUsers(page);
		UserPOJO user = new UserPOJO(idUser, "michael.lawson@reqres.in", "Michael", "Lawson",
				"https://reqres.in/img/faces/7-image.jpg");
		UserPOJO userResponse = createUserFromList(idUser, positionInList, response);
		Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
		Assertions.assertEquals(user.getEmail(), userResponse.getEmail());
		Assertions.assertEquals(user.getFirst_name(), userResponse.getFirst_name());
		Assertions.assertEquals(user.getLast_name(), userResponse.getLast_name());
		Assertions.assertEquals(user.getAvatar(), userResponse.getAvatar());
	}

	@Test
	@Description("Validate the status code when inserting a new user")
	void validateCreationStatusCodeValidUser() {
		String name = "john";
		String job = "analist";
		Response response = registerUser(name, job);
		Assertions.assertEquals(HttpStatus.SC_CREATED, response.statusCode());
	}

	@Test
	@Description("Validate the persistence of information in the registration of a new user")
	void validateUserCreationAndPersistence() {
		final String name = "mary";
		final String job = "teacher";
		Response response = registerUser(name, job);
		final String nameResponse = response.getBody().jsonPath().get("name");
		final String jobResponse = response.getBody().jsonPath().get("job");
		Assertions.assertEquals(HttpStatus.SC_CREATED, response.statusCode());
		Assertions.assertEquals(name, nameResponse);
		Assertions.assertEquals(job, jobResponse);
	}

	@Test
	@Description("Validate GET method for single user query")
	void getSingleUserById() {
		int userId = 2;
		UserPOJO user = new UserPOJO(userId, "janet.weaver@reqres.in", "Janet", "Weaver",
				"https://reqres.in/img/faces/2-image.jpg");
		Response response = getUserById(user.getId());
		UserPOJO userResponse = createSingleUser(userId, response);
		Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
		Assertions.assertEquals(user.getEmail(), userResponse.getEmail());
		Assertions.assertEquals(user.getFirst_name(), userResponse.getFirst_name());
		Assertions.assertEquals(user.getLast_name(), userResponse.getLast_name());
		Assertions.assertEquals(user.getAvatar(), userResponse.getAvatar());
	}

	@Test
	@Description("Validate GET method for querying a single user that doesn't exist")
	void getSingleUserNotFound() {
		int id = 23;
		Response res = getUserById(id);
		Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, res.statusCode());
	}

	@Test
	@Description("Validate PUT method to update user information")
	void validatePutUpdate() {
		String name = "morpheus";
		String job = "zion resident";
		int id = 2;
		Response response = putUser(name, job, id);
		final String nameResponse = response.getBody().jsonPath().get("name");
		final String jobResponse = response.getBody().jsonPath().get("job");
		Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
		Assertions.assertEquals(name, nameResponse);
		Assertions.assertEquals(job, jobResponse);
	}

	@Test
	@Description("Validate PATCH method to update user information")
	void validatePatch() {
		String name = "diana";
		String job = "nurse";
		int id = 2;
		Response response = patchUser(name, job, id);
		final String nameResponse = response.getBody().jsonPath().get("name");
		final String jobResponse = response.getBody().jsonPath().get("job");
		Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
		Assertions.assertEquals(name, nameResponse);
		Assertions.assertEquals(job, jobResponse);
	}

	@Test
	@Description("Validate the DELETE method to delete a user from the base")
	void deleteUser() {
		int id = 2;
		Response response = deleteUser(id);
		Assertions.assertEquals(HttpStatus.SC_NO_CONTENT, response.statusCode());
	}

	@Test
	@Description("Validate GET method to get the list of Users by Delay")
	void validateGetAllUsersPagedDelay() {
		int delay = 3 * 1000;
		int positionInList = 0; 
		int idUser = 7; 
		Response response = getlistUsersDelay(delay);
		int maximumValueAddition = 1000;
		boolean checkTime = response.getTime() <= delay + maximumValueAddition;
		UserPOJO user = new UserPOJO(1, "george.bluth@reqres.in", "George", "Bluth",
				"https://reqres.in/img/faces/1-image.jpg");
		UserPOJO userResponse = createUserFromList(idUser, positionInList, response);
		Assertions.assertTrue(checkTime);
		Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
		Assertions.assertEquals(user.getEmail(), userResponse.getEmail());
		Assertions.assertEquals(user.getFirst_name(), userResponse.getFirst_name());
		Assertions.assertEquals(user.getLast_name(), userResponse.getLast_name());
		Assertions.assertEquals(user.getAvatar(), userResponse.getAvatar());
	}

	private UserPOJO createUserFromList(int id, int position, Response response) {
		final String emailResponse = response.getBody().jsonPath().get("data[" + position + "].email");
		final String firstNameResponse = response.getBody().jsonPath().get("data[" + position + "].first_name");
		final String lastNameResponse = response.getBody().jsonPath().get("data[" + position + "].last_name");
		final String avatarResponse = response.getBody().jsonPath().get("data[" + position + "].avatar");
		UserPOJO userResponse = new UserPOJO(id, emailResponse, firstNameResponse, lastNameResponse, avatarResponse);
		return userResponse;
	}

	private UserPOJO createSingleUser(int id, Response response) {
		final String emailResponse = response.getBody().jsonPath().get("data.email");
		final String firstNameResponse = response.getBody().jsonPath().get("data.first_name");
		final String lastNameResponse = response.getBody().jsonPath().get("data.last_name");
		final String avatarResponse = response.getBody().jsonPath().get("data.avatar");
		UserPOJO userResponse = new UserPOJO(id, emailResponse, firstNameResponse, lastNameResponse, avatarResponse);
		return userResponse;
	}

}
