package tests;

import core.driver.BrowserEnum;
import core.driver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setup(BrowserEnum browser) {
        driver = WebDriverFactory.getDriver(browser);
    }

    @AfterMethod
    public void clearCookie() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}