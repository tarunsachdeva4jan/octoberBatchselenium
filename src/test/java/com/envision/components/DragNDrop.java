package com.envision.components;

import com.envision.login.tests.BeforeAfterAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DragNDrop extends BeforeAfterAnnotations {

    @Test
    public void validateDragNDropUsingInBuiltMethod() throws InterruptedException {
        Url ="https://demoqa.com/dragabble";
        driver.get(Url);

        WebElement axisTab =driver.findElement(By.id("draggableExample-tab-axisRestriction"));
        axisTab.click();
        Thread.sleep(2000);
        WebElement xAxis =driver.findElement(By.id("restrictedX"));
        WebElement yAxis = driver.findElement(By.id("restrictedY"));

        Actions action = new Actions(driver);
        action.dragAndDrop(xAxis,yAxis).build().perform();

        Thread.sleep(3000);
    }

    @Test
    public void validateDragNDropUsingMouseActions() throws InterruptedException {
        Url ="https://demoqa.com/dragabble";
        driver.get(Url);

        WebElement axisTab =driver.findElement(By.id("draggableExample-tab-axisRestriction"));
        axisTab.click();
        Thread.sleep(2000);
        WebElement xAxis =driver.findElement(By.id("restrictedX"));
        WebElement yAxis = driver.findElement(By.id("restrictedY"));

        Actions action = new Actions(driver);
        action.clickAndHold(xAxis).moveToElement(yAxis).release(xAxis)
                        .build()
                                .perform();

        Thread.sleep(3000);
    }

    @Test
    public void validateDragNDropUsingMoveByOffSetActions() throws InterruptedException {
        Url ="https://demoqa.com/dragabble";
        driver.get(Url);

        WebElement dragBox =driver.findElement(By.id("dragBox"));
        Thread.sleep(3000);
        Actions action = new Actions(driver);
        action.clickAndHold(dragBox).moveByOffset(400,52).release(dragBox)
                        .build().perform();

        Thread.sleep(3000);
    }

    public  void readTable(){
       List<WebElement> allRows = driver.findElements(By.tagName("tr"));
       Map<String,String> rowData = new HashMap<String,String>();
       for(int i =0;i<allRows.size();i++){
          List<WebElement> allColumns = allRows.get(i).findElements(By.tagName("td"));
          rowData.put(allColumns.get(0).getText(),allColumns.get(1).getText());
       }

    }

}
