package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/test/resources/features", glue = { "stepDefinition" }, plugin = {
        "html:target/cucumber-html-report.html", "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
        "junit:target/cucumber-results.xml" })

public class TestParallelRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
