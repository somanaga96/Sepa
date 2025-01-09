package stepDefinitions;

import io.cucumber.java.en.Given;
import utilities.BaseUtils;

public class MyStepdefs extends BaseUtils {
    @Given("user on Login page")
    public void userOnLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }
}
