package pageObjects;

import core.util.WaiterWrapperClass;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage {

    /**
     * Variables, constants
     */
    private static final String SIGNINPAGE_URL = "http://localhost:8080";

    /**
     * Receiving driver for the page
     * @param driver should be passed here
     */
    public SignInPage(WebDriver driver) {
        super(driver);
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
    @Override
    @Step("Open Sign In Page")
    public SignInPage openPage() {
        driver.get(SIGNINPAGE_URL);
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
}