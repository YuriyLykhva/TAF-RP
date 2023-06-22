package core.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static core.driver.WebDriverFactory.getDriver;

public class JsExecutor {

    protected void jsClickElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    protected void jsViewElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
