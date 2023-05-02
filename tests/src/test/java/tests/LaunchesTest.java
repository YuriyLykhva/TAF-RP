package tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.LaunchesPage;

import static core.driver.WebDriverFactory.getDriver;

public class LaunchesTest extends BaseTest {

    @Test
    public void openLaunchesPageTest() {
        new LaunchesPage().openPage();
        WebElement element = getDriver().findElement(By.xpath("//div[text()='All launches']"));
        Assert.assertTrue(element.isDisplayed());
    }
}