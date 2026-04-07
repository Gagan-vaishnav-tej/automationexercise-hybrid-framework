package validators;

import org.testng.Assert;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class ProductValidator extends CommonValidator{
	
	public void validateProductSchema(Response response) {
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/products-schema.json"));
	}
	
	public void validateSearchedProductName(Response response, String category) {
		String categoryValue = response.jsonPath().get("products[0].category.category");
		Assert.assertEquals(categoryValue, category);
	}
}
