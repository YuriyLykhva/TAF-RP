package tests.Selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static core.driver.WebDriverFactory.clearDriver;
import static core.driver.WebDriverFactory.getDriver;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class SelenideBaseTest {

    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true)
            .to("target/Selenide-screenshots");

    @BeforeAll
    public static void configSetup() {
        Configuration.reportsFolder = "build/test-result/reports";
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));
    }
    @BeforeEach
    public void setup() {
        WebDriverRunner.setWebDriver(getDriver());
        getDriver().manage().window().maximize();
        open("http://localhost:8080");
        $(By.xpath("//*[@placeholder=\"Login\"]")).val("superadmin");
        $(By.xpath("//*[@placeholder=\"Password\"]")).val("erebus").pressEnter();
    }

    @AfterEach
    public void tearDown() {
        clearDriver();
        closeWebDriver();
    }
}
