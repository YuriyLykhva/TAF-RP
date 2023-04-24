package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static core.driver.WebDriverFactory.clearDriver;
import static core.driver.WebDriverFactory.getDriver;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        getDriver().manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
        getDriver().quit();
        clearDriver();
    }
}