package com.envision.automation.tests.orangecrmapp.logintests;

import com.envision.application.orangecrm.pages.HomePage;
import com.envision.application.orangecrm.pages.LoginPage;
import com.envision.automation.core.DataProviderArgs;
import com.envision.automation.core.DataProviderUtils;
import com.envision.automation.core.Retry;
import com.envision.automation.core.TestAssert;
import com.envision.automation.tests.orangecrmapp.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


    @DataProviderArgs(value = "loginTestData=username,password,accountName")
    @Test(dataProvider = "jsonDataProvider", dataProviderClass = DataProviderUtils.class)
    public void validatePositiveLoginFlow(String username, String password, String accountName) throws Exception {
        //Fluent Object Page - extension of POM where we use method chaining..
        LoginPage loginPage = new LoginPage(driver.get());
        HomePage homePage = loginPage
                .navigateToWebsite()
                .enterUsername(username)
                .enterPassword(password)
                .hitSubmit();
        TestAssert.checkIfTrue(homePage.isDashboardHeadingDisplayed());
    }

    @DataProviderArgs(value = "loginTestData=username,password,accountName")
    @Test(dataProvider = "jsonDataProvider", dataProviderClass = DataProviderUtils.class)
    public void validateNegativeFlow(String username, String password, String accountName) throws Exception {
        //Fluent Object Page - extension of POM where we use method chaining..
        LoginPage loginPage = new LoginPage(driver.get());
        HomePage homePage = loginPage
                .navigateToWebsite()
                .enterUsername(username)
                .enterPassword(password)
                .hitSubmit();
        TestAssert.checkIfFalse(homePage.isDashboardHeadingDisplayed());
    }



}
