package businessObjects;

import core.model.User;
import org.openqa.selenium.WebDriver;
import pageObjects.SignInPage;

public class LoginBO {
    /**
     * This class is intended to implement business logic into TA framework but not finished yet...
     */
    public LoginBO loginIntoReportPortal(WebDriver driver, String login, String password) {

        new SignInPage(driver)
                .typeLogin(login)
                .typePassword(password)
                .clickLoginButton();

        return this;
    }

    public LoginBO loginIntoReportPortalWithUser(WebDriver driver, User user) {

        new SignInPage(driver)
                .typeLogin(user.getLogin())
                .typePassword(user.getPassword())
                .clickLoginButton();

        return this;
    }
}