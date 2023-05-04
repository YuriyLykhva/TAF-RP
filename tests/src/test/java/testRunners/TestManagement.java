package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"StepDefinitions"},
//        glue = {"StepDefinitions","Hooks"},
        tags = "@GetTest or @Test2",
        plugin = {"pretty",
                "html:target/SystemTestReports/html/report.html",
                "json:target/SystemTestReports/json/report.json",
//                "xml:target/SystemTestReports/junit/report.xml"

        }
//        monochrome = true,
//        dryRun = true
)
public class TestManagement {
}
