package com.automationexercise.api.validators;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserValidator extends CommonValidator{
	public void validateUserResponseSchema(Response response) {
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user-schema.json"));
	}
}

