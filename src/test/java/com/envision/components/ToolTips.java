package com.envision.components;

import com.envision.login.tests.BeforeAfterAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ToolTips extends BeforeAfterAnnotations {

    @Test
    public void validateToolTipText() throws InterruptedException {
        Url ="https://demoqa.com/tool-tips";
        driver.get(Url);

        WebElement hoverButton =driver.findElement(By.id("toolTipButton"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverButton).build().perform();

        WebElement toolTipText = driver.findElement(By.className("tooltip-inner"));
        Assert.assertEquals(toolTipText.getText(),"You hovered over the Button");

        Thread.sleep(2000);
    }
}
