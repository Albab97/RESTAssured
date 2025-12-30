package org;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class ParsingJSONResponseData {
    @Test(priority = 1)
    void testJSONResponse(){
        //Approach 1
        /* given()
                .contentType("ContentType.JSON")
        .when()
                .get("http://localhost:3000/store")
        .then()
                .statusCode(200)
                .header("Content-Type","application/json")
                // to validate a single field
                .body("book[3].title",equalTo("Clean Code")); // for complex json files, use online json path finder to write the json path.
*/
        //Approach 2 : more preferable
        Response res = given()
                .contentType(ContentType.JSON)
        .when()
                .get("http://localhost:3000/store");

        Assert.assertEquals(res.getStatusCode(),200);   //validation 1
        Assert.assertEquals(res.header("Content-Type"),"application/json");     //validation 2
        String bookName = res.jsonPath().get("book[3].title").toString();
        Assert.assertEquals(bookName,"Clean Code");         //validation 3
    }
    @Test(priority = 2)
    void testJSONResponseBodyData(){
        Response res = given()
                .contentType(ContentType.JSON)
        .when()
                .get("http://localhost:3000/store");

        //JSONObject class
        JSONObject jo = new JSONObject(res.asString());    // converting res from Response type to JSONObject type so that we could traverse

        // Validation 1 - Search if a particular book is present in the store.
        boolean status = false;
        for (int i=0;i<jo.getJSONArray("book").length();i++){
            String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
//            System.out.println(bookTitle);
            if(bookTitle.equals("Meditations")){
                status = true;
                break;
            }
        }
//        if (status) System.out.println("Book is present");
//        else System.out.println("Book is not present");
        Assert.assertEquals(status, true);

        // Validation 2 - Validate total price of all the books.
        double sum=0;
        for (int i=0; i< jo.getJSONArray("book").length(); i++){
            sum+=((Number)jo.getJSONArray("book").getJSONObject(i).get("price")).doubleValue();
        }
        System.out.println(sum);
        Assert.assertEquals(sum,89.23);
    }
}
