import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static utilities.Constants.FEATURE_FILE;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {FEATURE_FILE},
        glue = {"stepDefinitions"},
        tags = "@Login",
        plugin = {"pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        monochrome = true
)
public class TestRunner {
}
