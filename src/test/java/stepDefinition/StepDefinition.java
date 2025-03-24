package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;


import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils {
    static RequestSpecification res;
    static ResponseSpecification resSpec;
    static Response response;
    static String place_Id;
    private TestDataBuild testData = new TestDataBuild();
//    private Utils utils = new Utils();
    @Given("Add Place Payload {string} {string} {string}")
    public void add_place_payload(String address, String name, String language) throws FileNotFoundException {
        res = given().spec(requestSpecification())
                .body(testData.addPlacePayLoad(address,name,language));
    }
    @When("User calls {string} with {string} Http Request")
    public void user_calls_with_post_http_request(String resource,String method) {
//        ApiResources.valueOf(resource);
        System.out.println(ApiResources.valueOf(resource).getResource());
        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
        if(method.equalsIgnoreCase("post")){
            response = res.when().post(ApiResources.valueOf(resource).getResource());
        }
        else if(method.equalsIgnoreCase("Delete")){
            response = res.when().delete(ApiResources.valueOf(resource).getResource());
        }
        else if (method.equalsIgnoreCase("get")){
            response = res.when().get(ApiResources.valueOf(resource).getResource());
        }
//                .then()
//                .spec(resSpec).extract().response();
    }

    @Then("the Api call got success with Status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(),200);
    }

    @Then("{string} in Response Body is {string}")
    public void in_response_body_is(String expectedKey, String expectedValue) {
        String actualValue = getJsonValue(response,expectedKey);
        assertEquals(expectedValue,actualValue);
    }

    @Then("Verify placeId created that maps to {string} using {string}")
    public void verify_place_id_created_that_maps_to_using(String expectedName, String resource) throws FileNotFoundException {
        place_Id = getJsonValue(response,"place_id");
        System.out.println(place_Id);
        res = given().spec(requestSpecification())
                .queryParam("place_id",place_Id);
        user_calls_with_post_http_request(resource,"get");
        String actualName = getJsonValue(response,"name");
        assertEquals(expectedName,actualName);
    }
    @Given("DeletePlace Payload")
    public void delete_place_payload() throws FileNotFoundException {
        System.out.println(place_Id);
        res = given().spec(requestSpecification())
                .body(testData.deletePayload(place_Id));
    }
}
