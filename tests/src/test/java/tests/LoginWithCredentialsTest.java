package tests;

import businessObjects.LoginBO;
import core.util.DataProviderClass;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Feature("Login with credentials Test")
public class LoginWithCredentialsTest extends BaseTest {

    /**
     * This class is intended to transfer credentials from CSV-file into TA framework but not finished yet...
     */
    private LoginBO loginBO = new LoginBO();

    //todo: test is disabled

    @Test(enabled = false, dataProvider = "credentials", dataProviderClass = DataProviderClass.class)
    @Description("This test verifies that user with correct credentials is logged in")
    public void loginViaCredentialsTest(String login, String password) {

//        loginBO.loginIntoReportPortal(login, password);
        /*
        Assertion logic
         */

    }
}