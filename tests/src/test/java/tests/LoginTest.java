package tests;

import core.model.User;
import core.util.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SignInPage;

import static core.driver.WebDriverFactory.getDriver;

public class LoginTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void openSignInPage() throws InterruptedException {
        new SignInPage().openPage();
        String signInPageTitle = getDriver().getTitle();
        Thread.sleep(2000);
        Assert.assertEquals(signInPageTitle, "Report Portal");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginWithUserViaModel() throws InterruptedException {
        User user = User.createUser();
        new SignInPage()
                .openPage()
                .typeLogin(user.getLogin())
                .typePassword(user.getPassword())
                .clickLoginButton();
        String mainPageTitle = getDriver().getTitle();
        Thread.sleep(2000);
        Assert.assertEquals(mainPageTitle, "Report Portal");
    }
}