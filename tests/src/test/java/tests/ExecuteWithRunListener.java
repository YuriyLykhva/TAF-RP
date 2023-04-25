package tests;

import core.util.JUnitTestListener;
import org.junit.runner.JUnitCore;

public class ExecuteWithRunListener {
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new JUnitTestListener());
        runner.run(LoginTest.class, ApiTest.class);
    }
}
