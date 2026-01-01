package chaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser {
    @Test
    void testDeleteUser(ITestContext context){
//        int id = (int) context.getAttribute("user_id");
        int id = (int) context.getSuite().getAttribute("user_id");

        String token = "GOREST_TOKEN_KEY";
        given()
                .header("Authorization","Bearer "+token)
                .pathParam("id",id)
                .when()
                .delete("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(204)
                .log().body();
    }
}
