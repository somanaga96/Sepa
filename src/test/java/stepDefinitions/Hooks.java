package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.BaseUtils;

public class Hooks extends BaseUtils {
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Scenario name :"+scenario.getName());
        createWebDriver(scenario);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
