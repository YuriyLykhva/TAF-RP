package tests;

import core.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SignInPage;

import static core.driver.WebDriverFactory.getDriver;

public class LoginTest extends BaseTest {

    @Test
    public void openSignInPage() {
        new SignInPage().openPage();
        String signInPageTitle = getDriver().getTitle();
        Assert.assertEquals(signInPageTitle, "Report Portal");
    }

    @Test
    public void loginWithUserViaModel() {
        User user = User.createUser();
        new SignInPage()
                .openPage()
                .typeLogin(user.getLogin())
                .typePassword(user.getPassword())
                .clickLoginButton();
        String mainPageTitle = getDriver().getTitle();
        Assert.assertEquals(mainPageTitle, "Report Portal");
    }
}