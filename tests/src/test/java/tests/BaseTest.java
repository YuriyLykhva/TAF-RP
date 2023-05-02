package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import static core.driver.WebDriverFactory.clearDriver;
import static core.driver.WebDriverFactory.getDriver;

public class BaseTest {

    @BeforeClass
    public static void setup() {
        getDriver().manage().window().maximize();
    }

    @AfterClass
    public static void tearDown() {
        getDriver().quit();
        clearDriver();
    }
}