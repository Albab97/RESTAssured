package org;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Logging {
    @Test(priority = 1)
    void testLogs(){
        given()
                .header("x-api-key","reqres_eaa82eb699c94baebf499f82f8cbccd5")
        .when()
                .get("https://reqres.in/api/users?page=2")
        .then()
//                .log().body(); // to print the response body only
//                .log().cookies(); // to print only cookies
//                .log().headers(); // to print only headers
                .log().all(); // to print everything
    }
}
