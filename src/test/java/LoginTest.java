import core.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SignInPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void openSignInPage() {
        new SignInPage(driver)
                .openPage();
        String signInPageTitle = driver.getTitle();
        Assert.assertEquals(signInPageTitle, "Report Portal");
    }

    @Test(priority = 100)
    public void loginWithUserViaModel() {
        User user = User.createUser();
        new SignInPage(driver)
                .openPage()
                .typeLogin(user.getLogin())
                .typePassword(user.getPassword())
                .clickLoginButton();
        String mainPageTitle = driver.getTitle();
        Assert.assertEquals(mainPageTitle, "Report Portal");
    }
}