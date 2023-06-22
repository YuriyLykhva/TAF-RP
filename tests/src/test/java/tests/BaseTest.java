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
}