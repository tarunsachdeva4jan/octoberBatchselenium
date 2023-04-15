package com.envision.components;

import com.envision.login.tests.BeforeAfterAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Scrolling extends BeforeAfterAnnotations {

    @Test
    public void validateScrolling() throws InterruptedException {
        Url ="https://demoqa.com";
        driver.get(Url);
        WebElement bookStoreElement =driver.findElement(By.xpath("//h5[text() = 'Book Store Application']"));
        //Step 1; convert driver into javascript executor type
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        //step 2: execute your command to scroll in method (executeScript());
        //scroll all the way down to the bottom of the page
        javascriptExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight);");
        Thread.sleep(3000);
        //scroll all the way up to the top of the page
        javascriptExecutor.executeScript("window.scrollTo(0,-document.body.scrollHeight);");
        Thread.sleep(3000);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",bookStoreElement);
        Thread.sleep(3000);

    }

}
