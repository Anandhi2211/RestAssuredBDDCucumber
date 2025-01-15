package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

import java.io.FileNotFoundException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeDeleteScenario() throws FileNotFoundException {
        StepDefinition stepDefinition = new StepDefinition();

        if(StepDefinition.place_Id == null) {
            stepDefinition.add_place_payload("Cincinnati","Barb","English");
            stepDefinition.user_calls_with_post_http_request("AddplaceApi","post");
            stepDefinition.verify_place_id_created_that_maps_to_using("Barb","GetPlaceApi");

        }
          }
}
