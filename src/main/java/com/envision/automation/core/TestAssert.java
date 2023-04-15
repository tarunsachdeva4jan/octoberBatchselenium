package com.envision.automation.core;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TestAssert {

    public static void checkIfEquals(String expected, String actual){
        Assert.assertEquals(expected,actual, "Values Mismatch. Expected["+expected+"], but actual["+actual+"]");
    }

    public static void checkIfNotEquals(String expected, String actual){
        Assert.assertNotEquals(expected,actual, "Values Matching, not different. Expected["+expected+"], but actual["+actual+"]");
    }


    public static void checkIfTrue(boolean value){
        Assert.assertTrue(value, "Values Mismatch. Expected["+value+"]");
    }

    public static void checkIfFalse(boolean value){
        Assert.assertFalse(value, "Values Mismatch. Expected False but found["+value+"]");
    }

    public static void checkIfDisplayed(IWebActions actions,String elementName) throws Exception {
        WebElement element =actions.findWebElement(elementName);
        Assert.assertTrue(element.isDisplayed(),"Element is not displayed.");
    }

    public static void checkIfNotDisplayed(WebElement element){
        Assert.assertFalse(element.isDisplayed(),"Element is displayed.");
    }

}
