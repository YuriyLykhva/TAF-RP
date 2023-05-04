package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class SystemHooks {

    @Before
    public void BeforeDisplayMessage(Scenario scenario) {
        System.out.println("Before " + scenario.getName());
    }

    @After
    public void AfterDisplayMessage(Scenario scenario) {
        System.out.println("After " + scenario.getName());
    }
}
