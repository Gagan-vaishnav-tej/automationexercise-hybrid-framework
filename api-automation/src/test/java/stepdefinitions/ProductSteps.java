package stepdefinitions;

import clients.ProductApiClient;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import validators.ProductValidator;

public class ProductSteps {
	
	private ProductApiClient productApiClient;
	private ProductValidator productValidator;
	private String productName;
	private Response response;
	
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
	    response = productApiClient.getAllProducts();	    
	}

	@Then("response status code should be {int}")
	public void validateStatusCode(Integer code) {
	    productValidator.validateStatusCode(response, code);
	}

	@Then("response should match product JSON schema")
	public void validateProductsJsonSchema() {
	    productValidator.validateProductSchema(response);
	}
	
	@Given("user prepares search product form parameter with {string}")
	public void searchProduct(String string) {		
		productName = string;
	}

	@When("user sends POST request to search product API")
	public void postSearchProductApi() {
		response = productApiClient.getSingleProduct(productName);
	}

	@Then("response should contain searched product details")
	public void validateSearchProduct() {
	    productValidator.validateSearchedProductName(response, productName);
	}

	@Then("response should match search product JSON schema")
	public void validateSearchProductJsonSchema() {
		productValidator.validateProductSchema(response);
	}

}
