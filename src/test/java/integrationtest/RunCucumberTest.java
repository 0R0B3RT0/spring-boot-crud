package integrationtest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", features = "classpath:src/test/resources/cucumber/*")
public abstract class RunCucumberTest {
}
