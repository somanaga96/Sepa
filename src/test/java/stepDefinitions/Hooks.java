package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.BaseUtils;

public class Hooks extends BaseUtils {
    @Before
    public void setUp(Scenario scenario) {
        createWebDriver(scenario);
    }

//    @After
//    public void tearDown() {
//        if (driver != null) {
//            driver.close();
//        }
//    }
}
