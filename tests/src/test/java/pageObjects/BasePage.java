package pageObjects;

import org.openqa.selenium.WebDriver;

import static core.driver.WebDriverFactory.getDriver;

public abstract class BasePage {
    protected WebDriver driver = getDriver();

        /**
     * Abstract method
     * @return mandatory method to be implemented in child pages
     */

    protected BasePage() {
        driver.manage().window().maximize();
    }
}