package businessObjects;

import core.model.User;
import pageObjects.SignInPage;

public class LoginBO {
    /**
     * This class is intended to implement business logic into TA framework but not finished yet...
     */
    public LoginBO loginIntoReportPortal(String login, String password) {

        new SignInPage()
                .typeLogin(login)
                .typePassword(password)
                .clickLoginButton();

        return this;
    }

    public LoginBO loginIntoReportPortalWithUser(User user) {

        new SignInPage()
                .typeLogin(user.getLogin())
                .typePassword(user.getPassword())
                .clickLoginButton();

        return this;
    }
}