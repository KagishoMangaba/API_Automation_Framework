package org.kagisho.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.kagisho.steps"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class  TestRunnerTest {

}
