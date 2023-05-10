package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static core.driver.WebDriverFactory.clearDriver;
import static core.driver.WebDriverFactory.getDriver;

public class SystemHooks {

    @Before
    public void BeforeDisplayMessage(Scenario scenario) {
        System.out.println("Before " + scenario.getName());
    }

    @After
    public void AfterDisplayMessage(Scenario scenario) {
        getDriver().quit();
        clearDriver();
    }
}
