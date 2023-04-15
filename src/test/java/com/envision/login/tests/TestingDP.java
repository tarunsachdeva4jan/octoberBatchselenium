package com.envision.login.tests;

import com.envision.automation.core.DataProviderArgs;
import com.envision.automation.core.DataProviderUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestingDP {



    //step2: provider dataProviderClass and name
    @DataProviderArgs(value="loginTestData=username,password,accountName")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testPositiveLoginFlow(String username, String password,String accountName) throws IOException, InterruptedException {
        System.out.println(username + "="+password+"="+accountName);
    }

    @DataProviderArgs(value="5")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "fakeLoginDataProvider")
    public void testPositiveLoginFlowUsingFaker(String username,String password) throws IOException, InterruptedException {
        System.out.println(username+"="+password);

        ///login scripts steps here;::
    }


    @DataProviderArgs(value="registrationTestData=firstName,lastName,phoneNumber")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testRegistrationData(String firstName, String lastName,String phoneNumber) throws IOException, InterruptedException {
        System.out.println(firstName + "="+lastName+"="+phoneNumber);
    }


}
