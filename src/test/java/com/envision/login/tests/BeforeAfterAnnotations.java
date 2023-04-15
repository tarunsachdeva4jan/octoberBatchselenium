package com.envision.login.tests;

import com.envision.automation.core.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BeforeAfterAnnotations {


    public WebDriver driver;
    public String Url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";


    @BeforeSuite
    public void intializeFrameworkConfigurations() throws IOException {
        ConfigReader.loadFrameworkProperties();
    }

    //Open browser, maximize it, set timeouts
    @BeforeMethod
    public void doSomethingBeforeEveryTestMethod(){
        System.setProperty("webdriver.chrome.driver",ConfigReader.chromeBinaryPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(ConfigReader.mediumWaitTime, TimeUnit.SECONDS); //page load wait, wait for page to load
        driver.manage().timeouts().implicitlyWait(ConfigReader.longWaitTime,TimeUnit.SECONDS); //implicit wait (default), wait for all elements upta a max time defined as parameter
        driver.navigate().to(Url);

    }


    //Close the browser
    @AfterMethod
    public void doSomethingAfterEveryTestMethod(){
        driver.close();
    }


}
