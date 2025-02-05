import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static utilities.Constants.FEATURE_FILE;
import static utilities.Constants.STEP_DEFINITION;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {FEATURE_FILE},
        glue = {STEP_DEFINITION},
        tags = "@Login",
        plugin = {"pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        monochrome = true
)
public class TestRunner {
}
