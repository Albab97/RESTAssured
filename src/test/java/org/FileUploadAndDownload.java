package org;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class FileUploadAndDownload {
    @Test (priority = 1)
    void singleFileUpload(){
        File myFile = new File("C:\\Users\\HP\\Desktop\\Work\\Software Testing Domain\\API Automation Testing\\orders_csv_data.csv");

        given()
                .multiPart("file",myFile)       //if you compare with Postman this is same as choosing form-data and adding key value pair of file and actual file
                .contentType("multipart/form-data")
        .when()
                .post("http://localhost:8080/uploadFile")
        .then()
                .statusCode(200)
                .body("fileName",equalTo("orders_csv_data.csv"))
                .log().all();
    }

//    @Test
    void multipleFilesUpload(){
        File file1 = new File("C:\\Users\\HP\\Desktop\\Work\\Software Testing Domain\\API Automation Testing\\orders_csv_data.csv");
        File file2 = new File("C:\\Users\\HP\\Desktop\\Work\\Software Testing Domain\\API Automation Testing\\csvjson.json");
        given()
                .multiPart("files",file1)
                .multiPart("files",file2)
        .when()
                .post("http://localhost:8080/uploadMultipleFiles")
        .then()
                .statusCode(200)
                .body("[1].fileName",equalTo("csvjson.json"))
                .body("[0].fileName",equalTo("orders_csv_data.csv"))
                .log().all();
    }
    @Test(priority = 2)
    void fileDownload(){
        given()
        .when()
                .get("http://localhost:8080/downloadFile/orders_csv_data.csv")
        .then()
                .statusCode(200)
                .log().all();
    }
}
