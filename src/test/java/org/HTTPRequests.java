package org;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/*
Given() : content type, set cookies, add auth, add param, set headers info etc.
When() : get, post, put, delete
Then() : validate status code, extract response, extract headers cookies & response body
*/
 public class HTTPRequests {
     int id;
    @Test
    void getUsers(){
        // whichever method starts first will not have dot. other methods following that will have a dot.
        // As of now given is empty so no issues. We can also remove it
                given()
                        .header("x-api-key","reqres_eaa82eb699c94baebf499f82f8cbccd5")
                .when()
                        .get("https://reqres.in/api/users?page=2")
                .then()
                        .statusCode(200)
                        .body("page",equalTo(2))
                        .log().all();
    }

    @Test(priority = 2)
    void createUser(){
        HashMap data = new HashMap();
        data.put("name","Morpheus");
        data.put("job","Serve God");

        id = given()
                .header("x-api-key","reqres_eaa82eb699c94baebf499f82f8cbccd5")
                .contentType("application/json")
                .body(data)
        .when()
                .post("https://reqres.in/api/users")

                .jsonPath().getInt("id"); // to capture the json response(value) of a particular field and store in variable id.

//        .then()       // .then() method is To validate the response.
//                .statusCode(201)
//                .log().all();
    }

    @Test(priority = 3, dependsOnMethods = {"createUser"}) // dependsOnMethods means that updateUser function will only run after createUser because it is dependent on that.
    void updateUser(){
        HashMap data = new HashMap();
        data.put("name","Peter Parker");
        data.put("job","Serve People");

                given()
                    .header("x-api-key","reqres_eaa82eb699c94baebf499f82f8cbccd5")
                    .contentType("application/json")
                    .body(data)
                .when()
                    .put("https://reqres.in/api/user/"+id)
                .then()
                        .statusCode(200)
                        .log().all();
    }
    @Test(priority = 4)
    void deleteUser(){
        given()
                .header("x-api-key","reqres_eaa82eb699c94baebf499f82f8cbccd5")
        .when()
                .delete("https://reqres.in/api/user/"+id)
        .then()
                .statusCode(204)
                .log().all();
    }
}
