package com.envision.automation.utils;

import com.github.javafaker.Faker;

public class FakeDataGenerator {

    public String getUsername(){
        return new Faker().name().username();
    }

    public String getFirstName(){
        return new Faker().name().firstName();
    }

    public String getLastName(){
        return new Faker().name().lastName();
    }

    public String getCellNumber(){
        return new Faker().phoneNumber().cellPhone();
    }

    public String getResidenceAddress(){
        return new Faker().address().fullAddress();
    }

    public String getPassword(){
        return new Faker().internet().password(4,10,true,true,true);
    }
}
