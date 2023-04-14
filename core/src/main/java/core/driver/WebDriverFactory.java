package core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ThreadGuard;

public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();
            driver.set(ThreadGuard.protect(new ChromeDriver()));
        }
        return driver.get();
    }
    //todo: firefox

    public static void clearDriver() {
        driver.remove();
    }

    /*

    private static WebDriver driver;

    public static WebDriver getDriver(BrowserEnum browser) {

        if (driver == null) {
            switch (browser) {
                case CHROME: {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                }
                case FIREFOX: {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                }
                default: {
                    throw new RuntimeException("Browser not supported: " + browser);
                }
            }
        }
        return driver;
    }*/
}