package builder;

import constants.ApiEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {
	
	public RequestSpecification buildRequest() {
		return RestAssured.given()
				.baseUri(ApiEndpoints.base_url);
	}

	public Response get(String endpoint) {
		return buildRequest()
				.when()
					.get(endpoint)
				.then()
					.log().ifError()
					.extract().response();
	}
	
	public Response post(String endpoint, String pathParameter) {
		return buildRequest()
				.contentType("application/x-www-form-urlencoded")
				.formParam("search_product", pathParameter)
				.when()
					.post(endpoint);
	}
}

