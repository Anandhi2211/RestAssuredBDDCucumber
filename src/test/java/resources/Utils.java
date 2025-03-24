package resources;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    private static RequestSpecification reqSpec;
    public RequestSpecification requestSpecification() throws FileNotFoundException {

        if(reqSpec==null){
                PrintStream printStream = new PrintStream(new FileOutputStream("logging.txt"));
                reqSpec = new RequestSpecBuilder()
                        .setBaseUri(getGlobalProperties("baseUrl"))
                        .setContentType(ContentType.JSON)
                        .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                        .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                        .addQueryParam("key","qaclick123")
                        .build();
            return reqSpec;
        }
        return reqSpec;
    }
    public static String getGlobalProperties(String key){
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\anand\\OneDrive\\Desktop\\Anandhi_Learning\\RestAssuredBDDCucumberFramework\\src\\test\\java\\resources\\global.properties");
            properties.load(fileInputStream);
            properties.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);

    }
     public String getJsonValue(Response response, String key){
         JsonPath js = new JsonPath(response.asString());
         return js.getString(key);
     }
}
