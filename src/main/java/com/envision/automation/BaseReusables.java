package com.envision.automation;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseReusables {

    static String screenshotPath = "/Users/ruchitachauhan/IdeaProjects/ContinousTesting/Framework/OctoberBatchSeleniumProject/src/test/resources/screenshots/";

    public static void takeScreenshot(WebDriver driver) throws IOException {
        TakesScreenshot takeScreenshot = (TakesScreenshot) driver;//convert the driver into takescreenshot interface
        File image =takeScreenshot.getScreenshotAs(OutputType.FILE);  //get the screenshot using takescreenshot variable
        FileUtils.copyFile(image,new File(screenshotPath
                +"Image-"+new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss").format(
                        new Date())
                +".png")
        );
    }
}
