package com.envision.automation.tests.orangecrmapp;

import com.envision.automation.core.*;
import com.envision.automation.utils.MySqlDbUtils;
import com.envision.automation.utils.PropertiesUtil;
import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BaseTest implements ITestListener {

    //public WebDriver driver;
    public ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public IWebActions actions;
    public Connection connection = null;
    public MySqlDbUtils sqlDbUtils = new MySqlDbUtils();
    public ResultSet rs = null;

    @BeforeSuite(alwaysRun = true)
    public void loadAllConfigurations() throws IOException, SQLException, ClassNotFoundException {
        ExtentManager.getReporter();
        ConfigReader.loadFrameworkProperties();
        if (ConfigReader.connectDb) {
            connection = sqlDbUtils.getConnection(ConfigReader.dbUrl, ConfigReader.dbUsername, ConfigReader.dbPassword); //combination of host, port and jdbc keyword
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void initialiseBrowser(Method method) throws Exception {
        ExtentTestManager.startTest(method.getName(),"");
        // driver = DriverManager.launchBrowser();
        driver.set(DriverManager.launchBrowser());

        //actions = new WebActions(driver);
        actions = new WebActions(driver.get());
        if(ConfigReader.connectDb) {
            Properties properties = new PropertiesUtil().loadAllProperties(System.getProperty("user.dir")
                    + "/src/test/resources/sqlQueries.properties");
            String query = properties.getProperty(method.getName());
            rs = sqlDbUtils.getResultSet(connection, query);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUpTests() throws Exception {
       // driver.close();
        Reporter.logStepWithScreenshot("Test Case finished",actions.takeScreenshot());
        driver.get().close();
        ExtentTestManager.stopTest();
    }


    @AfterSuite (alwaysRun = true)
    public void tearDownEverything() throws Exception{
        ExtentManager.getReporter().flush();
        ExtentManager.getReporter().close();
    }


}
