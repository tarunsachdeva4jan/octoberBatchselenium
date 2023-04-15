package com.envision.components;

import com.envision.login.tests.BeforeAfterAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class DatePicker extends BeforeAfterAnnotations {


    @Test
    public void validateDatePickerByEnteringDateInTextBox() throws InterruptedException {
        Url ="https://demoqa.com/date-picker";
        driver.get(Url);
        //Step 1: select element
        WebElement datePickerMonthYearInput =driver.findElement(By.id("datePickerMonthYearInput"));
        //Step 2: form a date
        Date latestDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate =sdf.format(latestDate);
        //Step 3: form a composite action to clear the text box
        Actions actions = new Actions(driver);
        actions.click(datePickerMonthYearInput)
                .keyDown(Keys.COMMAND)
                .sendKeys("a")
                .keyUp(Keys.COMMAND)
                .sendKeys(Keys.DELETE)
                .build().perform();
        //Step 4: type in the new date
        datePickerMonthYearInput.sendKeys(formattedDate + Keys.ENTER);

        Thread.sleep(5000);
    }


    @Test
    public void validateDatePickerManually() throws InterruptedException {
        Url = "https://demoqa.com/date-picker";
        driver.get(Url);
        //Step 1: select element
        WebElement datePickerMonthYearInput = driver.findElement(By.id("datePickerMonthYearInput"));
        datePickerMonthYearInput.click();
        Thread.sleep(2000);
        LocalDateTime ldt = LocalDateTime.now()
                .plusMonths(2)
                .plusYears(3)
                .plusDays(15);

        String month =ldt.getMonth().toString();//APRIL, MAY, JUNE => A + pril => April
        month = month.substring(0,1) + month.substring(1).toLowerCase();
        String year = String.valueOf(ldt.getYear());
        int day = ldt.getDayOfMonth();
        WebElement monthElement =driver.findElement(By.className("react-datepicker__month-select"));
        Select monthDropDown = new Select(monthElement);
        monthDropDown.selectByVisibleText(month);
        Thread.sleep(3000);
        WebElement yearElement =driver.findElement(By.className("react-datepicker__year-select"));
        Select yearDropDown = new Select(yearElement);
        yearDropDown.selectByVisibleText(year);
        Thread.sleep(3000);
        List<WebElement> daysOfMonthElement = driver.findElements(By.xpath("//div[contains(@class, 'react-datepicker__day--') and not(contains(@class, 'outside-month'))]"));

        for(WebElement element: daysOfMonthElement){ //10 =/=10
            if(element.getText().equalsIgnoreCase(String.valueOf(day))){
                element.click();
                break;
            }
        }
        Thread.sleep(5000);
    }

}
