package org;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Authentications {
    @Test
    void basicAuth(){
        given()
                .auth().basic("postman","password")
        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }
    @Test
    void digestAuth(){
        given()
                .auth().digest("postman","password")
        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }
    @Test
    void preemptiveAuth(){
        given()
                .auth().preemptive().basic("postman","password")
        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }
    @Test
    void bearerTokenAuth(){
        String bearerToken = "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";
//        given()
//                .header("Authorization","Bearer "+bearerToken)
//                .when()
//                .get("https://api.github.com/user/repos")
//                .then()
//                .statusCode(200)
//                .log().all();
        // Printing all the repo names present in your Github
        Response res = given()
                .header("Authorization","Bearer "+bearerToken)
                .when()
                .get("https://api.github.com/user/repos");
        JSONArray data = new JSONArray(res.asString());
        for (int i = 0; i < data.length(); i++) {
            String repoName = data.getJSONObject(i).get("name").toString();
            System.out.println(repoName);
        }
    }
    @Test
    void oAuth1(){
        given()
                .auth().oauth("consumerKey","consumerSecret","accessToken","tokenSecret")   //for oauth1 we need these details from developers.
                .when()
                .get("url")
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test
    void oauth2(){
        given()
                .auth().oauth2("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")
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test
    void APIKeyAuth(){
//        Method 1
//        given()
//                .queryParam("appid","9693db11c0f8b84631d2e6e33dfe219b")
//                .when()
//                .get("https://api.openweathermap.org/data/2.5/weather?q=Delhi")
//                .then()
//                .statusCode(200)
//                .log().all();
//        Method 2
            given()
                    .queryParam("appid","9693db11c0f8b84631d2e6e33dfe219b")
                    .pathParam("mypath","data/2.5/weather")
                    .queryParam("q","Delhi")
                    .when()
                    .get("https://api.openweathermap.org/{mypath}") // path parameter we have to mention in curly braces, but no need to mention query params.
                    .then()
                    .statusCode(200)
                    .log().all();
    }
}
