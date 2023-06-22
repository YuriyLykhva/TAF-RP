package core.util;

import core.driver.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log("Test '" + iTestResult.getName() + "' PASSED");
        if(iTestResult.getTestClass().getName().endsWith("LoginTest")) {
            saveScreenshot();
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log("Test '" + iTestResult.getName() + "' FAILED");
        saveScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log("Test '" + iTestResult.getName() + "' SKIPPED");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log("Test Started....");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log("Test finished");
    }

    private void saveScreenshot() {
        File screenCapture = ((TakesScreenshot) WebDriverFactory
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/"
                            + getCurrentTimeAsString() +
                            ".png"));
        } catch (IOException e) {
            log("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private void log(String methodName) {
        System.out.println(methodName);
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}