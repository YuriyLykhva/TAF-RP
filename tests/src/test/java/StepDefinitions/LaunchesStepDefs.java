package StepDefinitions;

import core.model.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.LaunchesPage;
import pageObjects.SignInPage;

import static core.driver.WebDriverFactory.clearDriver;
import static core.driver.WebDriverFactory.getDriver;

public class LaunchesStepDefs {

    @Given("I have Signed in user")
    public void i_have_signed_in_user() {
        User user = User.createUser();
        new SignInPage().loginWithUserViaModel(user);
    }
    @When("I click on Launches button")
    public void i_click_on_launches_button() {
        new LaunchesPage().openLaunchesPage();
    }
    @Then("Launches page opens")
    public void launches_page_opens() {
        WebElement element = getDriver().findElement(By.xpath("//div[text()='All launches']"));
        Assert.assertTrue(element.isDisplayed());
    }
    @And("Browser is closed")
    public void browserIsClosed() {
        getDriver().quit();
        clearDriver();
    }
}
