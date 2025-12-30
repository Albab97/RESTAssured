package org;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ParsingXMLResponseData {
    @Test
    void testXMLResponse() {
        //Approach 1
        /*given()
        .when()
                .get("https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml")
        .then()
                .statusCode(200)
                .header("content-type", "application/xml")
                .body("rss.channel.item[0].title",equalTo("New Year’s Eve Concerts at Kennedy Center Are Canceled"));
    */

        // Approach 2
        Response res =
                given()
                        .when()
                        .get("https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml");
        Assert.assertEquals(res.getStatusCode(),200);           // Validation 1
        Assert.assertEquals(res.header("content-type"),"application/xml");          // Validation 2
        String titleName = res.xmlPath().get("rss.channel.item[0].title").toString();
        Assert.assertEquals(titleName,"New Year’s Eve Concerts at Kennedy Center Are Canceled");            // Validation 3

    }
    @Test
    void testXMLResponseBody(){
        Response res =
                given()
                        .when()
                        .get("https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml");
        // XMLPath Class object
        // Validation 1 : To verify the total number of items in the list

        XmlPath xmlobj = new XmlPath(res.asString());       // converting res from Response type to XMLPath type so that we could traverse

        List<String> items = xmlobj.getList("rss.channel.item");
        Assert.assertEquals(items.size(),24);

        // Validation 2 : To search if a particular title is present or not
        boolean status = false;
        List<String> titleNames = xmlobj.getList("rss.channel.item.title");
        for (String s : titleNames){
//            System.out.println(s);
            if (s.equals("Four Takeaways From the New York Times Profile of Marjorie Taylor Greene")) {
                status = true;
                break;
            }
        }
        if(status) System.out.println("Title is found");
        else System.out.println("Title not found");
    }
}