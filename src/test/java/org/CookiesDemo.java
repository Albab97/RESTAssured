package org;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class CookiesDemo {
//    @Test(priority = 1)
    void testCookies(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .cookie("AEC","AaJma5vdxKd9rgRvAmi039KWnmuSgGcupFw4e8Gs0bvUYs2Wdgn35eKcYDM") //it should fail because value of this cookie will update every time we send the request.
                .log().all();
    }
    @Test(priority = 2)
    void getCookieInfo(){
        Response res = given()
                .when()
                .get("https://www.google.com/");

        //get single cookie value
//        String cookie_value = res.getCookie("AEC");
//        System.out.println("Value of Cookie is -> "+cookie_value);

        //get all Cookies info
        Map<String,String> cookies_values = res.getCookies();
        for (String k : cookies_values.keySet()){
            String cookie_value = res.getCookie(k);
            System.out.println(k+"      "+cookie_value);
        }
    }
}
