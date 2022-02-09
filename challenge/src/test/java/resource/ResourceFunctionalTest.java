
package resource;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;

@Epic("Regression test")
@Feature("Resource tests")
public class ResourceFunctionalTest extends ResourceBase {

	@Test
	@Description("Validate GET method for Resource list")
	void validateListOfResource() {
		int positionInList = 0; 
		Response response = getListResource();
		ResourcePOJO resource = new ResourcePOJO(1, "cerulean", 2000, "#98B2D1", "15-4020");
		Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
		ResourcePOJO resourceResponse = createResourceOfList(positionInList, response);
		Assertions.assertEquals(resource.getId(), resourceResponse.getId());
		Assertions.assertEquals(resource.getName(), resourceResponse.getName());
		Assertions.assertEquals(resource.getYear(), resourceResponse.getYear());
		Assertions.assertEquals(resource.getColor(), resourceResponse.getColor());
		Assertions.assertEquals(resource.getPantone_value(), resourceResponse.getPantone_value());
	}

	@Test
	@Description("Validate GET method for single Resource")
	void validateSingleResource() {
		int id = 2;
		Response response = getSingleResource(id);
		ResourcePOJO resource = new ResourcePOJO(2, "fuchsia rose", 2001, "#C74375", "17-2031");
		ResourcePOJO resourceResponse = createSingleResource(response);
		Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
		Assertions.assertEquals(resource.getId(), resourceResponse.getId());
		Assertions.assertEquals(resource.getName(), resourceResponse.getName());
		Assertions.assertEquals(resource.getYear(), resourceResponse.getYear());
		Assertions.assertEquals(resource.getColor(), resourceResponse.getColor());
		Assertions.assertEquals(resource.getPantone_value(), resourceResponse.getPantone_value());
	}

	@Test
	@Description("Validate GET method for single Resource That doesn't exist")
	void validateSingleResourceNonexistent() {
		int id = 23;
		Response response = getSingleResource(id);
		Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
	}
	
	private ResourcePOJO createResourceOfList(int position, Response response) {
		final int idResponse = response.getBody().jsonPath().get("data[" + position + "].id");
		final String nameResponse = response.getBody().jsonPath().get("data[" + position + "].name");
		final int yearResponse = response.getBody().jsonPath().get("data[" + position + "].year");
		final String colorResponse = response.getBody().jsonPath().get("data[" + position + "].color");
		final String pantoneValueResponse = response.getBody().jsonPath().get("data[" + position + "].pantone_value");
		ResourcePOJO resourceResponse = new ResourcePOJO(idResponse, nameResponse, yearResponse, colorResponse,
				pantoneValueResponse);
		return resourceResponse;
	}
	
	private ResourcePOJO createSingleResource(Response response) {
		final int idResponse = response.getBody().jsonPath().get("data.id");
		final String nameResponse = response.getBody().jsonPath().get("data.name");
		final int yearResponse = response.getBody().jsonPath().get("data.year");
		final String colorResponse = response.getBody().jsonPath().get("data.color");
		final String pantoneValueResponse = response.getBody().jsonPath().get("data.pantone_value");
		ResourcePOJO resourceResponse = new ResourcePOJO(idResponse, nameResponse, yearResponse, colorResponse, pantoneValueResponse);
		return resourceResponse;
	}

}
