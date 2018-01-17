package testrunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by maheshboyalla on 16/01/2018.
 */

@RunWith(Cucumber.class)
@CucumberOptions( features = "src/test/resources", glue = "steps",format = "json:target/cucumber.json")
public class ValtechTestRunner {
}
