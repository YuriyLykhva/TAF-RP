package pageObjects;

import org.openqa.selenium.WebDriver;

import static core.driver.WebDriverFactory.getDriver;


public abstract class BasePage {
    protected WebDriver driver = getDriver();

    /**
     * Abstract method
     * @return mandatory method to be implemented in child pages
     */
    protected abstract BasePage openPage();

    /*
     * Receiving driver for the page
//     * @param driver should be passed here
     */
    protected BasePage() {
        driver.manage().window().maximize();
    }
}