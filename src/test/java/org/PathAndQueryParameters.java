package org;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PathAndQueryParameters {

//    https://reqres.in/api/users?page=2&id=5

    @Test
    void testPathAndQueryParameters(){
        given()
                .header("x-api-key","reqres_eaa82eb699c94baebf499f82f8cbccd5")
                .pathParam("mypath","users") //Path Parameters
                .queryParam("page",2)  //Query parameter
                .queryParam("id",5)    //Query Parameter
        .when()
                .get("https://reqres.in/api/{mypath}")
        .then()
                .statusCode(200)
                .log().all();
    }
}
