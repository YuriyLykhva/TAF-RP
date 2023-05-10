package StepDefinitions;

import io.cucumber.java8.En;
import pageObjects.ProfileMenu;
import pageObjects.SignInPage;

import static org.junit.Assert.assertEquals;

public class LoginStepsWithLambdas implements En {

    SignInPage signInPage = new SignInPage();

    public LoginStepsWithLambdas() {

        Given("I have a User", () -> {});

        When("user enters Login as {string}", (String login) -> {
            signInPage
                    .openPage()
                    .typeLogin(login);
        });

        When("Password as {string}", (String password) -> {
            signInPage
                    .typePassword(password)
                    .clickLoginButton();
        });

        Then("user is logged in as {string}", (String userName) -> {
            String loggedUserName = new ProfileMenu().getLoggedUserName();
            assertEquals(loggedUserName, userName);
        });
    }
}
