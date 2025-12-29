package org;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
/*
Different ways to create POST request body
1) POST request body using Hashmap
2) POST request body creation using org.json.
3) POST request body creation using POJO class
4) POST request using external json file data
*/

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreatePOSTRequestBody {

//    POST request body using Hashmap.

//    @Test (priority = 1)
    void PostRequestUsingHashmap(){
        HashMap data = new HashMap();
        data.put("name","Scott");
        data.put("age","18");
        data.put("grade","11th");

        String subjects[] = {"Finance", "Accounting", "Economics"};
        data.put("subjects",subjects);

        given()
                .contentType("application/json")
                .body(data)
        .when()
                .post("http://localhost:3000/students")
        .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("age",equalTo("18"))
                .body("grade",equalTo("11th"))
                .body("subjects[0]",equalTo("Finance"))
                .body("subjects[2]",equalTo("Economics"))
                .header("Content-Type","application/json")
                .log().all();
    }

//  POST request body using org.json library.
//@Test (priority = 1)
void PostRequestUsingOrgJSON(){

    JSONObject data = new JSONObject();
    data.put("name","Greg");
    data.put("age","19");
    data.put("grade","8th");
    String subjects[] = {"Hindi", "English", "French"};
    data.put("subjects",subjects);

    given()
            .contentType("application/json")
            .body(data.toString())
    .when()
            .post("http://localhost:3000/students")
    .then()
            .statusCode(201)
            .body("name",equalTo("Greg"))
            .body("age",equalTo("19"))
            .body("grade",equalTo("8th"))
            .body("subjects[0]",equalTo("Hindi"))
            .body("subjects[2]",equalTo("French"))
            .header("Content-Type","application/json")
            .log().all();
}

// POST request using POJO class
//    @Test (priority = 1)
    void testPostUsingPOJO(){
        Pojo_PostRequest data = new Pojo_PostRequest();
        data.setName("Arman");
        data.setAge("27");
        data.setGrade("4th year");
        String subjectsArr[] = {"C","C++"};
        data.setSubjects(subjectsArr);

        given()
                .contentType("application/json")
                .body(data)
        .when()
                .post("http://localhost:3000/students")
        .then()
                .statusCode(201)
                .body("name",equalTo("Arman"))
                .body("age",equalTo("27"))
                .body("grade",equalTo("4th year"))
                .body("subjects[0]",equalTo("C"))
                .body("subjects[1]",equalTo("C++"))
                .header("Content-Type","application/json")
                .log().all();
    }
    // POST request using external Json file
    @Test (priority = 1)
    void testPostUsingExternalJsonFile() throws FileNotFoundException {

        File f = new File(".\\body.json");
        FileReader fr= new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(data.toString())
        .when()
                .post("http://localhost:3000/students")
        .then()
                .statusCode(201)
                .body("name",equalTo("Rampage Jackson"))
                .body("age",equalTo("34"))
                .body("grade",equalTo("3rd Job"))
                .body("subjects[0]",equalTo("German"))
                .body("subjects[1]",equalTo("Spanish"))
                .header("Content-Type","application/json")
                .log().all();
    }
    @Test (priority = 2)
    void deleteRecord(){
        given()
        .when()
                .delete("http://localhost:3000/students/743c")
        .then()
                .statusCode(200);
    }
}
