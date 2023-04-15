package com.envision.components;

import com.envision.login.tests.BeforeAfterAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Buttons extends BeforeAfterAnnotations {

    @Test
    public void validateButton() throws InterruptedException {
        Url ="https://demoqa.com/buttons";
        driver.get(Url);

        WebElement doubleClickBtn =driver.findElement(By.id("doubleClickBtn"));
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickBtn).build().perform();
        Thread.sleep(2000);
        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
        actions.contextClick(rightClickBtn).build().perform();
        Thread.sleep(2000);
    }
}
