package pageObjects;

import core.model.User;
import core.util.WaiterWrapperClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LaunchesPage extends BasePage {
    public LaunchesPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    /**
     * Web Elements
     */
    @FindBy(xpath = "//a[@href='#superadmin_personal/launches']")
    private WebElement launches;

    @Override
    public LaunchesPage openPage() {
        User user = User.createUser();
        new SignInPage()
                .openPage()
                .typeLogin(user.getLogin())
                .typePassword(user.getPassword())
                .clickLoginButton();
        WaiterWrapperClass.waitForElement(driver, launches);
        launches.click();
        return this;
    }
}
