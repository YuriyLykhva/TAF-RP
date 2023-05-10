package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"StepDefinitions","Hooks"},
        tags = "@Login",
        plugin = {"pretty",
                "html:target/SystemTestReports/html/report.html",
                "json:target/SystemTestReports/json/report.json"
        }
)
public class TestManagement {
}
