package tests;

import core.util.RetryAnalyzer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LaunchesPage;

import static core.driver.WebDriverFactory.getDriver;

public class LaunchesTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void openLaunchesPage() {
        new LaunchesPage().openPage();
        WebElement element = getDriver().findElement(By.xpath("//div[text()='All launches']"));
        Assert.assertTrue(element.isDisplayed());
    }
}