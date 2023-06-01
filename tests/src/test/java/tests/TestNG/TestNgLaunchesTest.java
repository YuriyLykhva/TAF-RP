package tests.TestNG;

import core.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LaunchesPage;
import pageObjects.SignInPage;

import static core.driver.WebDriverFactory.getDriver;

public class TestNgLaunchesTest extends TestNgBaseTest {

    @Test
    public void openLaunchesPageTest() {
        User user = User.createUser();
        new SignInPage().loginWithUserViaModel(user);
        new LaunchesPage().openLaunchesPage();
        WebElement element = getDriver().findElement(By.xpath("//div[text()='All launches']"));
        Assert.assertTrue(element.isDisplayed());
    }
}