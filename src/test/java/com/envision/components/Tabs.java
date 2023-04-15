package com.envision.components;

import com.envision.login.tests.BeforeAfterAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Tabs extends BeforeAfterAnnotations {

    @Test
    public void validateTab() throws InterruptedException {
        Url ="https://demoqa.com/tabs";
        driver.get(Url);

        findATab(driver, "use").click();
        Thread.sleep(2000);
        findATab(driver,"what").click();
        Thread.sleep(2000);
        findATab(driver,"origin").click();
        Thread.sleep(2000);
    }

    public WebElement findATab(WebDriver driver, String tabName){
        WebElement tab =driver.findElement(By.id("demo-tab-"+tabName));
        return tab;
    }
}
