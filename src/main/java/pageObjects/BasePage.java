package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    protected WebDriver driver;
    public static final int WAIT_TIMEOUT_SECONDS = 10;

    /**
     * Abstract method
     * @return mandatory method to be implemented in child pages
     */
    protected abstract BasePage openPage();

    /**
     * Receiving driver for the page
     * @param driver should be passed here
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
    }

    protected void jsClickElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     * Scrolling to the element
     * @param element become visible
     */
    protected void jsViewElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}