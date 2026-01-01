package org;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerDataGenerator {
    @Test
    void generateDummyData(){
        Faker faker = new Faker();
        String fullname = faker.name().fullName();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();

        String username = faker.name().username();
        String password = faker.internet().password();
        String phonenumber = faker.phoneNumber().cellPhone();
        String email = faker.internet().safeEmailAddress();

        System.out.println(fullname);
        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(username);
        System.out.println(password);
        System.out.println(phonenumber);
        System.out.println(email);
    }
}
