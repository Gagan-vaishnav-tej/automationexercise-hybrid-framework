package com.automationexercise.api.clients;

import com.automationexercise.api.builder.RequestBuilder;
import com.automationexercise.api.constants.ApiEndpoints;
import io.restassured.response.Response;

public class ProductApiClient {
	
	private RequestBuilder requestBuilder;
	

	public ProductApiClient() {
		requestBuilder = new RequestBuilder();
	}


	public Response getAllProducts() {
		return requestBuilder.get(ApiEndpoints.getProducts_url);
	}
	
	public Response getSingleProduct(String parameter) {
		return requestBuilder.post(ApiEndpoints.getSingleProduct_url, parameter);
	}
}
