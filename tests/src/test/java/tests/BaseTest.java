package tests;

import org.testng.annotations.*;

import static core.driver.WebDriverFactory.clearDriver;
import static core.driver.WebDriverFactory.getDriver;

public class BaseTest {

    @BeforeMethod
    public void setup() {
        getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
        clearDriver();
    }







    /*
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

     */
}