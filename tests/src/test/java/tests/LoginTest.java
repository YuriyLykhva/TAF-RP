package tests;

import core.model.User;
import org.junit.Test;
import pageObjects.SignInPage;

import static core.driver.WebDriverFactory.getDriver;
import static org.junit.Assert.assertEquals;


public class LoginTest extends BaseTest {

    @Test
    public void openSignInPage() {
        new SignInPage().openPage();
        String signInPageTitle = getDriver().getTitle();
        assertEquals("Report Portal", signInPageTitle);
    }

    @Test
    public void loginWithUserViaModelTest() {
        User user = User.createUser();
        new SignInPage()
                .loginWithUserViaModel(user);
        String mainPageTitle = getDriver().getTitle();
        assertEquals("Report Portal", mainPageTitle);
    }
}