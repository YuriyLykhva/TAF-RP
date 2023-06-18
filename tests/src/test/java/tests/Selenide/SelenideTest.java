package tests.Selenide;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class SelenideTest extends SelenideBaseTest {

    @Test
    public void loginTest() {
        $(byText("All Dashboards")).shouldBe(visible);
        $("title").should(exist);
        assertEquals("Report Portal", title());
    }

    @Test
    public void openLaunchesTest() {
        $$(By.xpath("//*[@class='sidebarButton__sidebar-nav-btn--1prEO']")).get(1).click();
        $(byText("All launches")).shouldBe(visible);
    }

    @Test
    public void filtersTest() {
        $$(By.cssSelector("i.sidebarButton__btn-icon--1lJxI")).get(2).click();
        $$(By.xpath("//div[@class='gridRow__grid-row-wrapper--1dI9K']"))
                .shouldHave(size(10));
    }

//    @Test
    public void userProfileTest() throws InterruptedException {

        //todo        $(By.xpath("//img[@alt='avatar']")).shouldBe(enabled).click();
        Thread.sleep(5000);
        $(By.xpath("//img[@alt='avatar']")).click();
        $(By.xpath("//a[text()='Profile']")).click();

        $(By.xpath("//span[text()='User profile']")).shouldHave(text("User profile"));
        $(By.cssSelector("div.userInfo__login---ntMG")).shouldHave(text("superadmin"));
    }
}