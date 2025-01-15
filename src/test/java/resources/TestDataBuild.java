package resources;

import com.restassured.pojo.AddPlace;
import com.restassured.pojo.Location;

public class TestDataBuild {

    public AddPlace addPlacePayLoad(String address, String name, String language){
        AddPlace ad = new AddPlace();
        Location location = new Location();
        location.setLat("-38.383494");
        location.setLng("33.427362");
        ad.setAddress(address);
        ad.setAccuracy("50");
        ad.setLanguage(language);
        ad.setName(name);
        ad.setWebsite("http://google.com");
        ad.setLocation(location);
        ad.setPhone_number("(+91) 983 893 3937");
        ad.setTypes("shoe park");
        ad.setTypes("shop");
        return ad;
    }
    public String deletePayload(String place_id){

        return "{\n" +
                "    \"place_id\":\""+place_id+"\"\n" +
                "}";
    }
}
