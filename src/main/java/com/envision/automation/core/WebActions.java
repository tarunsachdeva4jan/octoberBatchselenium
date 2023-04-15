package com.envision.automation.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.*;

public class WebActions implements IWebActions {
    public WebDriver driver;

    public WebActions(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public By getBy(String locatorType, String locatorValue) throws Exception {
        By by = null;
        if (locatorType.equalsIgnoreCase("id")) {
            by = By.id(locatorValue);
        } else if (locatorType.equalsIgnoreCase("name")) {
            by = By.name(locatorValue);
        } else if (locatorType.equalsIgnoreCase("class")) {
            by = By.className(locatorValue);
        } else if (locatorType.equalsIgnoreCase("lt")) {
            by = By.linkText(locatorValue);
        } else if (locatorType.equalsIgnoreCase("plt")) {
            by = By.partialLinkText(locatorValue);
        } else if (locatorType.equalsIgnoreCase("xpath")) {
            by = By.xpath(locatorValue);
        } else if (locatorType.equalsIgnoreCase("css")) {
            by = By.cssSelector(locatorValue);
        } else if (locatorType.equalsIgnoreCase("tag")) {
            by = By.tagName(locatorValue);
        } else {
            throw new Exception("Invalid locator type [" + locatorType + "] provided, pls check the valid options");
        }
        return by;
    }




    @Override
    public WebElement findWebElement(String elementName) throws Exception { //LoginPage.tbxUsername
        WebElement element = null;
        try {
            Properties properties = ObjectRepoReader.loadAllLocators();
            String propertyValue = properties.getProperty(elementName); //xpath;//input[@name='abc']
            String locatorType = propertyValue.split(";")[0]; //xpath
            String locatorValue = propertyValue.split(";")[1];//input[@name='abc']
            By by = getBy(locatorType, locatorValue);
            WebDriverWait wait =
                    new WebDriverWait(driver, ConfigReader.longWaitTime);
             element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            scrollToElement(element);
        }catch(Exception e){
            Reporter.logFailedStep("Unable to find element ["+elementName+"], pls check the value");
        }
        return element;
    }

    @Override
    public List<WebElement> findAllWebElements(String elementName) throws Exception {
        List<WebElement> allElements = null;
        try {
            Properties properties = ObjectRepoReader.loadAllLocators();
            String propertyValue = properties.getProperty(elementName); //xpath;//input[@name='abc']
            String locatorType = propertyValue.split(";")[0]; //xpath
            String locatorValue = propertyValue.split(";")[1];//input[@name='abc']
            By by = getBy(locatorType, locatorValue);
            WebDriverWait wait =
                    new WebDriverWait(driver, ConfigReader.longWaitTime);
            allElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            scrollToElement(allElements.get(0));
        }catch(Exception e){
            Reporter.logFailedStep("Unable to find element ["+elementName+"], pls check the value");
        }
        return allElements;
    }

    @Override
    public void launchUrl(String url){
        try {
            driver.get(url);
            Reporter.logPassedStep("Url["+url+"] Launched successfully");

        }catch(Exception e){
            Reporter.logFailedStep("Unable to launch Url["+url+"]");

        }
    }

    @Override
    public void clickOn(String elementName) throws Exception {
        try {
            WebElement element = findWebElement(elementName);
            new WebDriverWait(driver, ConfigReader.longWaitTime)
                    .until(ExpectedConditions.elementToBeClickable(element))
                    .click();
            Reporter.logPassedStep("Clicked on element[ "+elementName+"] successfully");
        }catch(Exception e){
            Reporter.logFailedStep("Unable to click on element[ "+elementName+"]");

        }
    }

    @Override
    public void doubleClickOn(String elementName) throws Exception{
        try {
            Actions act = new Actions(driver);
            act.doubleClick(findWebElement(elementName))
                    .build().perform();
            Reporter.logPassedStep("Double Clicked on element[ "+elementName+"] successfully");

        }catch(Exception e){
            Reporter.logFailedStep("Unable to double click on element[ "+elementName+"]");

        }
    }

    @Override
    public void rightClickOn(String elementName) throws Exception{
        Actions act = new Actions(driver);
        act.contextClick(findWebElement(elementName))
                .build().perform();
    }


    @Override
    public void typeInto(String elementName, String text) throws Exception {
        try {
            WebElement element = findWebElement(elementName);
            new WebDriverWait(driver, ConfigReader.longWaitTime)
                    .until(ExpectedConditions.elementToBeClickable(element))
                    .click();
            Thread.sleep(200);
            element.clear();
            Thread.sleep(200);
            element.sendKeys(text);
            Reporter.logPassedStep("Typed["+text+"] into element["+elementName+"] successfully");

        }catch(Exception e){
            Reporter.logFailedStep("Unable to type["+text+"] into element["+elementName+"]");
            Reporter.logStepWithScreenshot("",takeScreenshot());
        }
    }

    @Override
    public String getTextFrom(String elementName) throws Exception {
        String text= null;
        try {
            WebElement element = findWebElement(elementName);
             text = element.getText();
            Reporter.logPassedStep("Text ["+text+"] Retrieved from element["+elementName+"] successfully");

        }catch(Exception e){
            Reporter.logFailedStep("Unable to get text from element["+elementName+"]");

        }
        return text;
    }

    @Override
    public boolean isWebElementDisplayed(String elementName) throws Exception{
        WebElement element = findWebElement(elementName);
        return element.isDisplayed();
    }


    @Override
    public String takeScreenshot() throws IOException {
        if(ConfigReader.takeScreenshot) {
            TakesScreenshot takeScreenshot = (TakesScreenshot) driver;//convert the driver into takescreenshot interface
            String image = takeScreenshot.getScreenshotAs(OutputType.BASE64);  //get the screenshot using takescreenshot variable
            return "data:image/png;base64,"+image;
        }
        return "";
    }

    @Override
    public void selectValueFromDropdown(String elementName, String how, String option) throws Exception {
       WebElement element= findWebElement(elementName);
        Select select = new Select(element);
        if(how.equalsIgnoreCase("value")){
            select.selectByValue(option);
        }else if(how.equalsIgnoreCase("text")){
            select.selectByVisibleText(option);
        }else if(how.equalsIgnoreCase("index")){
            select.selectByIndex(Integer.parseInt(option));
        }else{
            throw new Exception("invalid 'mode' provided to select option");
        }
    }

    @Override
    public Boolean checkIfOptionPresentInDropdown(String elementName, String how,String optionValue) throws Exception {
        WebElement element= findWebElement(elementName);
        Select select = new Select(element);
        List<WebElement> options =select.getOptions();
        for(WebElement option: options){
            String text = option.getText();
            if(text.equalsIgnoreCase(optionValue)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void switchToFrame(String elementName) throws Exception {
        driver.switchTo().frame(findWebElement(elementName));
    }

    @Override
    public void switchBackToMainPage() throws Exception{
        driver.switchTo().defaultContent();
    }

    @Override
    public void switchToLastWindow() throws Exception{
        Set<String> windows =driver.getWindowHandles();
        int length = windows.size();

        driver.switchTo().window(new ArrayList<String>(windows).get(length-1));
    }


    @Override
    public void switchToSecondLastWindow() throws Exception{
        Set<String> windows =driver.getWindowHandles();
        int length = windows.size();

        driver.switchTo().window(new ArrayList<String>(windows).get(length-2));
    }

    @Override
    public void closeAllChildWindows(String windowId) throws Exception{
        Set<String> windows =driver.getWindowHandles();
        int length = windows.size();
        List<String> sortedWindows =new ArrayList<String>(windows);
        for(int i=0;i<sortedWindows.size();i++){
            if(!sortedWindows.get(i).equals(windowId)){
                driver.switchTo().window(sortedWindows.get(i));
                driver.close();
            }
        }

        driver.switchTo().window(windowId);
    }

    @Override
    public void closeAllWindows() throws Exception{
        Set<String> windows =driver.getWindowHandles();
        int length = windows.size();
        List<String> sortedWindows =new ArrayList<String>(windows);
        for(int i=0;i<sortedWindows.size();i++)
        {
            driver.switchTo().window(sortedWindows.get(i));
            driver.close();
        }
    }

    @Override
    public void dragAndDropTo(String elementDragFrom, String elementDragTo) throws Exception{
        Actions act = new Actions(driver);
        act.dragAndDrop(findWebElement(elementDragFrom),findWebElement(elementDragTo))
                .build()
                .perform();
    }

    @Override
    public void dragAndDropToUsingMouse(String elementDragFrom, String elementDragTo) throws Exception{
        Actions act = new Actions(driver);
        act.moveToElement(findWebElement(elementDragFrom)).clickAndHold(findWebElement(elementDragFrom))
                .moveToElement(findWebElement(elementDragTo))
                .release(findWebElement(elementDragFrom))
                .build()
                .perform();
    }

    @Override
    public void mouseHoverTo(String elementName) throws Exception{
        Actions act = new Actions(driver);
        act.moveToElement(findWebElement(elementName))
                .build().perform();
    }

    @Override
    public void scrollToElement(WebElement elementName) throws Exception{
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);",elementName);
    }

    @Override
    public void scrollToTopOfPage() throws Exception{
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");
    }

    @Override
    public void scrollToBottomOfPage() throws Exception{
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,-document.body.scrollHeight);");
    }



}
