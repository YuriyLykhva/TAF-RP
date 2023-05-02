package pageObjects;

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

    public LaunchesPage openLaunchesPage() {
        WaiterWrapperClass.waitForElement(driver, launches);
        launches.click();
        return this;
    }
}