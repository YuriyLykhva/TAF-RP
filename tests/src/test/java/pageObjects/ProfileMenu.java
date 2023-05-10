package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static core.util.WaiterWrapperClass.waitForElement;
import static core.util.WaiterWrapperClass.waitUntilElementIsNotDisplayed;

public class ProfileMenu extends BasePage {
    public ProfileMenu() {
        super();
        PageFactory.initElements(driver, this);
    }

    /**
     * Web Elements
     */

    private final String notificationItemPath = "//*[@class=" +
            "'notificationItem__message-container--16jY2 notificationItem__success--Xv97a']";
    @FindBy(xpath = "//img[@alt='avatar']")
    private WebElement profile;
    @FindBy(xpath = "//*[@class='userBlock__username--2nzpE']")
    private WebElement userName;

    @FindBy(xpath = notificationItemPath)
    private WebElement notificationItem;

    @FindBys({@FindBy(xpath = notificationItemPath)})
    private List<WebElement> notificationItems;

    public String getLoggedUserName() {
        waitForElement(driver, notificationItem);
        waitUntilElementIsNotDisplayed(notificationItems);

        profile.click();
        waitForElement(driver, userName);

        return userName.getText();
    }
}