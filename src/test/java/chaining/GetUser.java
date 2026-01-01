package chaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUser {
    @Test
    void testGetUser(ITestContext context){
//        int id = (int) context.getAttribute("user_id");     // this id should come from the CreateUser request
        int id = (int) context.getSuite().getAttribute("user_id");
        String token = "GOREST_TOKEN_KEY";
        given()
                .header("Authorization","Bearer "+token)
                .pathParam("id",id)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().body();
    }
}
