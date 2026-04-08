package builder;

import java.util.HashMap;
import java.util.Map;

import constants.ApiEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.User;

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
					.post(endpoint)
				.then().log().ifError().extract().response();
	}
	
	public Response postCreateAccount(String endpoint, User user) {
	    Map<String, String> params = userToFormParams(user); 
	    RequestSpecification req = buildRequest().contentType("application/x-www-form-urlencoded");
	    params.forEach(req::formParam);
	    return req.when().post(endpoint)
	              .then().log().ifError()
	              .extract().response();
	}

	private Map<String,String> userToFormParams(User user){
	    Map<String,String> map = new HashMap<String,String>();
	    map.put("name", user.getName());
	    map.put("email", user.getEmail());
	    map.put("password", user.getPassword());
	    map.put("title", user.getTitle());
	    map.put("birth_date", user.getBirthDate());
	    map.put("birth_month", user.getBirthMonth());
	    map.put("birth_year", user.getBirthYear());
	    map.put("firstname", user.getFirstName());
	    map.put("lastname", user.getLastName());
	    map.put("company", user.getCompany());
	    map.put("address1", user.getAddress1());
	    map.put("country", user.getCountry());
	    map.put("state", user.getState());
	    map.put("city", user.getCity());
	    map.put("zipcode", user.getZipcode());
	    map.put("mobile_number", user.getMobileNumber());
	    return map;
	}

	public Response putUpdateAccount(String endpoint, User user)
	{
		Map<String, String>params=userToFormParams(user);
		RequestSpecification req=buildRequest().contentType("application/x-www-form-urlencoded");
		params.forEach(req::formParam);
		return req.when().put(endpoint)
	              .then().log().ifError()
	              .extract().response();
	}
	public Response deleteAccount(String endpoint, String email, String password) {
	    return buildRequest()
	            .contentType("application/x-www-form-urlencoded")
	            .formParam("email", email)
	            .formParam("password", password)
	            .when()
	            .delete(endpoint)
	            .then()
	            .log().ifError()
	            .extract().response();
	}

}

