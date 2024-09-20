package GitHubAPI;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Random;

public class AuthorizationAPITesting {
	

    
	@Test(description = "Get user details with valid token")
	@Description("This test checks if we can get user details with a valid token.")
	public void testGetUserWithValidToken() {

		RestAssured.baseURI = "https://api.github.com";

		
		String token = "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";

		
		RequestSpecification request = RestAssured.given();

		
		request.header("Authorization", "Bearer " + token);

		
		Response response = request.get("/user");

		
		if (response.getStatusCode() == 200) {
			System.out.println("Success! Status Code: " + response.getStatusCode());
		} else {
			System.out.println("Failed! Status Code: " + response.getStatusCode());
		}

		
		System.out.println("Response Body: " + response.getBody().asString());
	}
	@Test(description = "Get user details with  No Token Provided")
	@Description("This test checks if we can get user details with a  NoTokenProvided.")
    public void testNoTokenProvided() {

        
        RestAssured.baseURI = "https://api.github.com";

        
        RequestSpecification request = RestAssured.given();

        
        Response response = request.get("/user");

       
        System.out.println("Status Code: " + response.getStatusCode());

        
        Assert.assertEquals(response.getStatusCode(), 401, "Expected status code is 401 Unauthorized");

       
        System.out.println("Response Body: " + response.getBody().asString());
    }
	
	@Test(description = "Get user details with  Invalid Token Provided")
	@Description("This test checks if we can get user details with a  Invalid Token Provided.")
    public void testInvalidTokenProvided() {

        
        RestAssured.baseURI = "https://api.github.com";

       
        RequestSpecification request = RestAssured.given();

        
        String invalidToken = "ghp_invalidToken12345";

        
        request.header("Authorization", "Bearer " + invalidToken);

        
        Response response = request.get("/user");

        
        System.out.println("Status Code: " + response.getStatusCode());

        
        Assert.assertEquals(response.getStatusCode(), 401, "Expected status code is 401 Unauthorized");

        
        System.out.println("Response Body: " + response.getBody().asString());
    }
	@Test(description = "Get user details with valid  Token Without Necessary Permissions")
	@Description("This test checks if we can get user details with a Token Without NecessaryP ermissions.")
    public void testTokenWithoutNecessaryPermissions() {

       
        RestAssured.baseURI = "https://api.github.com";

       
        RequestSpecification request = RestAssured.given();

        
        String tokenWithoutPermissions1 = "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";

       
        request.header("Authorization", "Bearer " + tokenWithoutPermissions1);

        
        Response response = request.get("/user/installations");

       
        System.out.println("Status Code: " + response.getStatusCode());

        
        Assert.assertEquals(response.getStatusCode(), 403, "Expected status code is 403 Forbidden");

        
        System.out.println("Response Body: " + response.getBody().asString());
    }
	


	@Test(description = "Update user bio with valid token")
	@Description("This test checks if we can update the user bio with a valid token.")
	public void testUpdateUserBioWithValidToken() {

	    // Set the base URI for GitHub API
	    RestAssured.baseURI = "https://api.github.com";

	    // Bearer token (replace with a valid one)
	    String token = "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";

	    // Create an array of possible bio strings
	    String[] bioOptions = {"QA", "SDET", "Automation Tester", "Software Engineer"};

	    // Generate a random index to select one bio string
	    Random random = new Random();
	    String dynamicBio = bioOptions[random.nextInt(bioOptions.length)];

	    // Create the request body to update bio with the dynamic value
	    String requestBody = "{\"bio\": \"" + dynamicBio + "\"}";

	    // Create Request Specification
	    RequestSpecification request = RestAssured.given();

	    // Add Bearer Token to the request header
	    request.header("Authorization", "Bearer " + token);

	    // Add the request body and set the content type to JSON
	    request.body(requestBody);
	    request.header("Content-Type", "application/json");

	    // Send PATCH Request to update the bio
	    Response response = request.patch("/user");

	    // Validate the status code
	    if (response.getStatusCode() == 200) {
	        System.out.println("Success! Status Code: " + response.getStatusCode());
	        System.out.println("Updated Bio: " + dynamicBio);
	    } else {
	        System.out.println("Failed! Status Code: " + response.getStatusCode());
	    }

	    // Print the response body
	    System.out.println("Response Body: " + response.getBody().asString());
	}

	
}

