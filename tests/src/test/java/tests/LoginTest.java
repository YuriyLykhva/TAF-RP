package tests;

import core.model.User;
import org.junit.jupiter.api.Test;
import pageObjects.SignInPage;

import static core.driver.WebDriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {

    @Test//(retryAnalyzer = RetryAnalyzer.class)
    public void openSignInPage() {
        new SignInPage().openPage();
        String signInPageTitle = getDriver().getTitle();
        assertEquals(signInPageTitle, "Report Portal");
    }

    @Test//(retryAnalyzer = RetryAnalyzer.class)
    public void loginWithUserViaModel() {
        User user = User.createUser();
        new SignInPage()
                .openPage()
                .typeLogin(user.getLogin())
                .typePassword(user.getPassword())
                .clickLoginButton();
        String mainPageTitle = getDriver().getTitle();
        assertEquals(mainPageTitle, "Report Portal");
    }
}