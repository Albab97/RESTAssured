package org;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import org.testng.annotations.Test;

public class SerializationDeserialization {
    //Serialization
    @Test
    void convertPojo2Json() throws JsonProcessingException {
        //created java object using POJO class
        Pojo_PostRequest data = new Pojo_PostRequest();
        data.setName("Sarah");
        data.setAge("23");
        data.setGrade("1st year");
        String subjectsArr[] = {"History","Journalism"};
        data.setSubjects(subjectsArr);

        //convert java object(POJO) to json object (serialization)
        ObjectMapper objMapper = new ObjectMapper();
        String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        System.out.println(jsondata);
    }
    //Deserialization
    @Test
    void convertJson2Pojo() throws JsonProcessingException {
        String jsonData = "{\n" +
                "  \"name\" : \"Sarah\",\n" +
                "  \"age\" : \"23\",\n" +
                "  \"grade\" : \"1st year\",\n" +
                "  \"subjects\" : [ \"History\", \"Journalism\" ]\n" +
                "}";

        // convert json data ---> Pojo object

        ObjectMapper objectMapper= new ObjectMapper();
        Pojo_PostRequest obj1 = objectMapper.readValue(jsonData, Pojo_PostRequest.class);
        System.out.println(obj1.getName());
        System.out.println(obj1.getAge());
        System.out.println(obj1.getSubjects()[1]);
    }
}
