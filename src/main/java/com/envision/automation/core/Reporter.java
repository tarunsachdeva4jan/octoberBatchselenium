package com.envision.automation.core;

import com.relevantcodes.extentreports.LogStatus;

public class Reporter {

    public static void logPassedStep(String message) {
        ExtentTestManager.getTest().log(LogStatus.PASS, message);
    }

    public static void logFailedStep(String message) {
        ExtentTestManager.getTest().log(LogStatus.FAIL, message);
    }

    public static void logStepWithScreenshot(String message,String base64Text) {
        ExtentTestManager.getTest().log(LogStatus.PASS,"Screenshot Captured: <br>: " +ExtentTestManager.getTest().addScreenCapture(base64Text));

    }

}