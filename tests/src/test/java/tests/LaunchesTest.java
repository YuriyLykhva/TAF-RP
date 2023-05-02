package tests;

import core.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.LaunchesPage;
import pageObjects.SignInPage;

import static core.driver.WebDriverFactory.getDriver;

public class LaunchesTest extends BaseTest {

    @Test
    public void openLaunchesPageTest() {
        User user = User.createUser();
        new SignInPage().loginWithUserViaModel(user);
        new LaunchesPage().openLaunchesPage();
        WebElement element = getDriver().findElement(By.xpath("//div[text()='All launches']"));
        Assert.assertTrue(element.isDisplayed());
    }
}