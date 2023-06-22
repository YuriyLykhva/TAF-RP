package core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.net.MalformedURLException;
import java.net.URL;

import static core.util.Constants.REMOTE_WEB_SERVER_URL;
import static core.util.Constants.USE_REMOTE_WEB_DRIVER;


public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        if (driver.get() == null) {
            if (USE_REMOTE_WEB_DRIVER) {

                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setBrowserName("chrome");
                desiredCapabilities.setPlatform(Platform.WINDOWS);

                try {
                    WebDriver remoteWebDriver =
                            new RemoteWebDriver(new URL(REMOTE_WEB_SERVER_URL), desiredCapabilities);
                    driver.set(ThreadGuard.protect(remoteWebDriver));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                WebDriverManager.chromedriver().setup();
                driver.set(ThreadGuard.protect(new ChromeDriver()));
            }
        }
        return driver.get();
    }
    public static void clearDriver() {
        driver.remove();
    }
}