package pageObjects;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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