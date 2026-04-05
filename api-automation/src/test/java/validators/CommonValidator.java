package validators;

import org.testng.Assert;

import io.restassured.response.Response;

public class CommonValidator {

	public void validateStatusCode(Response response, int code) {
		int statusCode = response.jsonPath().get("responseCode");
		Assert.assertEquals(code, statusCode);
	}
	
	public void validateResponseTime(Response response, long maxMillis) {
        long responseTime = response.getTime();
        Assert.assertTrue(responseTime < maxMillis,
            "Response time " + responseTime + "ms exceeded " + maxMillis + "ms");
    }

    public void validateContentType(Response response, String contentType) {
        Assert.assertEquals(response.getContentType(), contentType);
    }

    public void validateHeader(Response response, String header, String value) {
        Assert.assertEquals(response.getHeader(header), value);
    }
}
