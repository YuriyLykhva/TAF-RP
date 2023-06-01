package tests.TestNG;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static core.driver.WebDriverFactory.clearDriver;
import static core.driver.WebDriverFactory.getDriver;

public class TestNgBaseTest {

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