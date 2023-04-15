package com.envision.automation.core;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int MAX_RETRY = ConfigReader.maxRetryCount;
    int retryCount=0;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) { // Check if test not succeed
            if (retryCount < MAX_RETRY) { // Check if maxtry count is reached
                retryCount++; // Increase the maxTry count by 1
                result.setStatus(ITestResult.FAILURE); // Mark test as failed
                return true;
            } // Tells TestNG to re-run the test
            else {
                result.setStatus(ITestResult.FAILURE); // If maxCount reached,test marked as
            } // failed
        } else {
            result.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

}
