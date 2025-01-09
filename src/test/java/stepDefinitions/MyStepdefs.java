package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import locators.Login;
import utilities.BaseUtils;

public class MyStepdefs extends BaseUtils {
    Login loginForms = new Login(driver);

    @Given("user on Login page")
    public void userOnLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("user enter {string} and {string}")
    public void userEnterAnd(String username, String password) {
        System.out.println("pass :"+password);
        loginForms.enterUserNameAndPassword(username, password);
    }

    @And("user click on login button")
    public void userClickOnLoginButton() {

    }
}
