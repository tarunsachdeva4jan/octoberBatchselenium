package com.envision.login.tests;

import com.envision.automation.BaseReusables;
import com.envision.automation.core.DataProviderArgs;
import com.envision.automation.core.DataProviderUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OrangeHRM_LoginTestCases extends BeforeAfterAnnotations {



    //step2: provider dataProviderClass and name
    @DataProviderArgs(value="loginTestData=username,password,accountName")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testPositiveLoginFlow(String username, String password, String accountName) throws IOException, InterruptedException {

        //Step1: enter username: //input[@name='username']
        WebElement tbxUsername =driver.findElement(By.xpath("//input[@name='username']"));
        tbxUsername.sendKeys(username);
        //Step2: enter password: //input[@type='password' and @name = 'password']
        driver.findElement(By.xpath("//input[@type='password' and @name = 'password']"))
                .sendKeys(password);
        //step3: click Login : //button[text() = ' Login ']
        driver.findElement(By.xpath("//button[text() = ' Login ']")).click();
        //step4: validate you are on the home page: //h6[text()='Dashboard']
        WebElement headingDashboard =driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        Assert.assertTrue(headingDashboard.isDisplayed(),"Test Case failed!! Unable to login");
        Assert.assertEquals(headingDashboard.getText(),"Dashboard", "Text is something different");
        BaseReusables.takeScreenshot(driver);
        Thread.sleep(3000);

    }

    @DataProviderArgs(value="loginTestData=username,password,accountName")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testNegativeLoginFlow(String username, String password, String accountName) throws IOException, InterruptedException {

        //Step1: enter username: //input[@name='username']
        WebElement tbxUsername =driver.findElement(By.xpath("//input[@name='username']"));
        tbxUsername.sendKeys(username);
        //Step2: enter password: //input[@type='password' and @name = 'password']
        driver.findElement(By.xpath("//input[@type='password' and @name = 'password']"))
                .sendKeys(password);
        //step3: click Login : //button[text() = ' Login ']
        driver.findElement(By.xpath("//button[text() = ' Login123']")).click();
        //step4: validate you are on the home page: //h6[text()='Dashboard']
        BaseReusables.takeScreenshot(driver);
        Thread.sleep(3000);

    }



}
