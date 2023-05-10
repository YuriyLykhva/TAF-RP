package pageObjects;

import core.util.WaiterWrapperClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfileMenu extends BasePage {
    public ProfileMenu() {
        super();
        PageFactory.initElements(driver, this);
    }

    /**
     * Web Elements
     */
    @FindBy(xpath = "//img[@alt='avatar']")
    private WebElement profile;
    @FindBy(xpath = "//*[@class='userBlock__username--2nzpE']")
    private WebElement userName;

    public String getLoggedUserName() {
        //todo:



        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WaiterWrapperClass.waitForElement(driver, profile);
        Actions action = new Actions(driver);
        action.moveToElement(profile).perform();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        profile.click();
        WaiterWrapperClass.waitForElement(driver, userName);

        return userName.getText();
    }
}