package tests.Selenide;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.*;
import static core.driver.WebDriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class DragDropTest extends SelenideBaseTest {
    @Test
    public void dragAndDropTest() {
        int horizontalMove = 300, verticalMove = 50;
        int initDashboardsCount = $$(By.xpath("//div[@class='gridRow__grid-row--1pS-5']/a")).size();

        $$(By.cssSelector("i.sidebarButton__btn-icon--1lJxI")).get(0).click();
        $$(By.xpath("//div[@class='gridRow__grid-row--1pS-5']/a")).get(0).click();
        SelenideElement resize =
                $$(By.xpath("//span[@class='react-resizable-handle react-resizable-handle-se']")).get(3);

        new Actions(getDriver()).dragAndDropBy(resize, horizontalMove, verticalMove).build().perform();
        new Actions(getDriver()).dragAndDropBy(resize, -horizontalMove, -verticalMove).build().perform();
        int finalDashboardsCount = $$(By.xpath("//div[@class='gridRow__grid-row--1pS-5']/a")).size();
        assertTrue(initDashboardsCount == finalDashboardsCount);
    }
}
