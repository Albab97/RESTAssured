package chaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {
    @Test
    void testUpdateUser(ITestContext context){
        Faker faker = new Faker();

        JSONObject data = new JSONObject();

        data.put("name",faker.name().fullName());
        data.put("gender","male");
        data.put("email",faker.internet().safeEmailAddress());
        data.put("status","active");

        String token = "GOREST_TOKEN_KEY";
//        int id = (int) context.getAttribute("user_id");
        int id = (int) context.getSuite().getAttribute("user_id");
        given()
                .header("Authorization","Bearer "+token)
                .contentType("application/json")
                .pathParam("id",id)
                .body(data.toString())
                .when()
                .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().body();
    }
}
