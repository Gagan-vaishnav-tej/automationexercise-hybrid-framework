package validators;

import org.testng.Assert;
import io.restassured.response.Response;

public class DeleteUserAccountValidator {

    public void validateDeleteStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Delete status code mismatch");
    }

    public void validateDeleteResponseMessage(Response response, String expectedMessage) {
        Assert.assertTrue(
                response.asString().contains(expectedMessage),
                "Expected delete message not found in response"
        );
    }
}