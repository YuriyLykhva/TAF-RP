package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ProfileMenu;
import pageObjects.SignInPage;

import static org.junit.Assert.assertEquals;

public class LoginSteps {

    SignInPage signInPage = new SignInPage();


    @Given("I have a User")
    public void i_have_a_user() {
    }

    @When("user enters Login as {string}")
    public void user_enters_login_as(String login) {
        signInPage
                .openPage()
                .typeLogin(login);
    }

    @When("Password as {string}")
    public void password_as(String password) {
        signInPage
                .typePassword(password)
                .clickLoginButton();
    }

    @Then("user is logged in as {string}")
    public void user_is_logged_in_as(String userName) {
        String loggedUserName = new ProfileMenu().getLoggedUserName();
        assertEquals(loggedUserName, userName);
    }
}
