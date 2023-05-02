package pageObjects;

import core.model.User;
import core.util.WaiterWrapperClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.util.Constants.SIGNIN_PAGE_URL;

public class SignInPage extends BasePage {

    public SignInPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    /**
     * Web Elements
     */
    @FindBy(xpath = "//*[@placeholder=\"Login\"]")
    private WebElement loginField;

    @FindBy(xpath = "//*[@placeholder=\"Password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[text()=\"Login\"]")
    private WebElement loginButton;

    /**
     * Opening Sign In Page
     * @return Sign In Page opens
     */
    public SignInPage openPage() {
        driver.get(SIGNIN_PAGE_URL);
        WaiterWrapperClass.waitForElement(driver, loginField);
        return this;
    }
    public SignInPage typeLogin(String login) {
        loginField.sendKeys(login);
        return this;
    }

    public SignInPage typePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void loginWithUserViaModel(User user) {
        new SignInPage()
                .openPage()
                .typeLogin(user.getLogin())
                .typePassword(user.getPassword())
                .clickLoginButton();
    }
}