package org;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class HeadersDemo {
    @Test(priority = 1)
    void testHeaders(){
        given()
        .when()
                .get("https://www.google.com/")
        .then()
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .and() // this .and() is not mandatory to add but its a good practice to separate if we have different kind of validations
                .header("Content-Encoding","gzip")
                .and()
                .header("Server","gws");
    }
    @Test(priority = 2)
    void getHeaders(){
        Response res = given()
                .when()
                .get("https://www.google.com/");
        //get single header info
//        String headerValue = res.getHeader("Content-Type");
//        System.out.println("Value of header --> "+headerValue);
        // get all headers info
        Headers myHeaders = res.getHeaders();
        for (Header h : myHeaders){
            System.out.println(h.getName()+"     "+h.getValue());
        }
        // The above functionality which prints all headers is not much useful because .log().all() method will automatically print all the headers info.
    }
}
