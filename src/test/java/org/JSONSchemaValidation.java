package org;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class JSONSchemaValidation {
    @Test
    void jsonSchemaValidation(){
        given()
        .when()
                .get("http://localhost:3000/store")
        .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeschema.json"));       //your schema file should be in src/test/resources folder
    }
}
