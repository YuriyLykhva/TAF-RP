package core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        if (driver.get() == null) {

//            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.setCapability("browserVersion", "112");
//            chromeOptions.setCapability("platformName", "Windows 11");

            WebDriverManager.chromedriver().setup();
            driver.set(ThreadGuard.protect(new ChromeDriver()));
//            try {
//                driver.set(ThreadGuard.protect(
//                        new RemoteWebDriver(
//                                new URL("http://localhost:4444/"), chromeOptions)));
//            } catch (MalformedURLException e) {
//                throw new RuntimeException(e);
//            }
        }
        return driver.get();
    }
    //todo: firefox

    public static void clearDriver() {
        driver.remove();
    }
}

/*
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setCapability("browserVersion", "67");
chromeOptions.setCapability("platformName", "Windows XP");
WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), chromeOptions);
driver.get("http://www.google.com");
driver.quit();

 */