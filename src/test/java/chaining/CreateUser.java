package chaining;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

//We can't run individual classes in chaining because everything is linked , so we will run using testng.xml by running whole test suite.


public class CreateUser {
    @Test
    void testCreateUser(ITestContext context)       // ITestContext is a class in testng package.
    // It is available at the <test> level in testng.xml file , so all the classes should come under the same <test> and not outside it.
    // If we want it to be available at suite level then we have to attach getSuite() method with context object
    {
        Faker faker = new Faker();

        JSONObject data = new JSONObject();

        data.put("name",faker.name().fullName());
        data.put("gender","male");
        data.put("email",faker.internet().safeEmailAddress());
        data.put("status","inactive");

        String token = "GOREST_TOKEN_KEY";

        int id = given()
                .header("Authorization","Bearer "+token)
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");       // no need to capture entire response, just pick the "id" field which you need
        System.out.println("Generated id : "+id);
//        context.setAttribute("user_id",id);     //it is like creating a global variable which would work across all classes
        context.getSuite().setAttribute("user_id",id);  // it is valid at suite level while running from testng.xml. Now the context is accessible in the suite level, therefore we can now run in both testng1.xml and testng.xml
    }
}
