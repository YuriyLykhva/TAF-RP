package StepDefinitions;

import core.model.User;
import io.cucumber.java8.En;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.LaunchesPage;
import pageObjects.SignInPage;

import static core.driver.WebDriverFactory.clearDriver;
import static core.driver.WebDriverFactory.getDriver;

public class LaunchesStepsWithLambdas implements En {
    public LaunchesStepsWithLambdas () {

        Given("I have Signed in user", () -> {
            User user = User.createUser();
            new SignInPage().loginWithUserViaModel(user);
        });

        When("I click on Launches button", () -> {
            new LaunchesPage().openLaunchesPage();
        });

        Then("Launches page opens", () -> {
            WebElement element = getDriver().findElement(By.xpath("//div[text()='All launches']"));
            Assert.assertTrue(element.isDisplayed());
        });

        And("Browser is closed", () -> {
            getDriver().quit();
            clearDriver();
        });
    }
}
