package com.automationexercise.api.stepdefinitions;

import org.apache.logging.log4j.Logger;
import com.automationexercise.api.clients.ProductApiClient;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import logger.LogsFactory;

import com.automationexercise.api.validators.ProductValidator;

public class ProductSteps {
	
	private ProductApiClient productApiClient;
	private ProductValidator productValidator;
	private String productName;
	private Response response;
	private Logger logger = LogsFactory.getLogger(ProductSteps.class);
	
	public ProductSteps() {
		productApiClient = new ProductApiClient();
		productValidator = new ProductValidator();
	}
	
	@Before
	public void setUp() {
		response = null;
		productName = null;
	}

	@Given("user sends GET request to fetch all products")
	public void sendGetProductsApi() {
		logger.info("user sends GET request to fetch all products");
	    response = productApiClient.getAllProducts();	    
	}

	@Then("response status code should be {int}")
	public void validateStatusCode(Integer code) {
		logger.info("response status code should be " + code);
	    productValidator.validateStatusCode(response, code);
	}

	@Then("response should match product JSON schema")
	public void validateProductsJsonSchema() {
		logger.info("response should match product JSON schema");
	    productValidator.validateProductSchema(response);
	}
	
	@Given("user prepares search product form parameter with {string}")
	public void searchProduct(String string) {	
		logger.info("user prepares search product form parameter with "+ string);
		productName = string;
	}

	@When("user sends POST request to search product API")
	public void postSearchProductApi() {
		logger.info("user sends POST request to search product API");
		response = productApiClient.getSingleProduct(productName);
	}

	@Then("response should contain searched product details")
	public void validateSearchProduct() {
		logger.info("response should contain searched product details");
	    productValidator.validateSearchedProductName(response, productName);
	}

	@Then("response should match search product JSON schema")
	public void validateSearchProductJsonSchema() {
		logger.info("response should match search product JSON schema");
		productValidator.validateProductSchema(response);
	}

}
