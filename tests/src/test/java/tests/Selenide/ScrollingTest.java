package tests.Selenide;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Execution(ExecutionMode.CONCURRENT)
public class ScrollingTest extends SelenideBaseTest {
    @Test
    public void scrollingTest() {

        $$(By.cssSelector("i.sidebarButton__btn-icon--1lJxI")).get(0).click();

        String xpathExpression = "//div[@class='gridRow__grid-row--1pS-5']/a";

        SelenideElement lastDashboard = $$(By.xpath(xpathExpression)).last();
        lastDashboard.scrollIntoView(true).click();
        $(By.xpath("//span[@title='string97946']")).shouldBe(visible);
    }
}
