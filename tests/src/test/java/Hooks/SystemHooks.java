package Hooks;

import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;

import static core.driver.WebDriverFactory.clearDriver;
import static core.driver.WebDriverFactory.getDriver;

public class SystemHooks implements En {
    public SystemHooks (){

        Before((Scenario scenario) -> {
            System.out.println("Before " + scenario.getName());
        });

        After(() -> {
            getDriver().quit();
            clearDriver();
        });
    }

}
