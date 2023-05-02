package tests;

import core.model.User;
import io.github.artsok.RepeatedIfExceptionsTest;
import org.junit.Test;
import pageObjects.SignInPage;

import static core.driver.WebDriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest extends BaseTest {

    @Test
    @RepeatedIfExceptionsTest(repeats = 3)
    public void openSignInPage() {
        new SignInPage().openPage();
        String signInPageTitle = getDriver().getTitle();
        assertEquals("Report Portal", signInPageTitle);
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @Test
    public void loginWithUserViaModelTest() {
        User user = User.createUser();
        new SignInPage()
                .loginWithUserViaModel(user);
        String mainPageTitle = getDriver().getTitle();
        assertEquals("Report Portal", mainPageTitle);
    }
}