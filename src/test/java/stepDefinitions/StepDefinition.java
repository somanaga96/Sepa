package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import locators.Login;
import utilities.BaseUtils;

import static utilities.BaseUtils.allureScreenShot;
import static utilities.BaseUtils.driver;

public class StepDefinition {
    Login loginForms = new Login(driver);

    @Given("user on Login page")
    public void userOnLoginPage() {
        driver.get("https://www.saucedemo.com/");
        allureScreenShot("home page");
    }

    @When("user enter {string} and {string}")
    public void userEnterAnd(String username, String password) {
        System.out.println("pass :" + password);
        loginForms.enterUserNameAndPassword(username, password);
    }

    @And("user click on login button")
    public void userClickOnLoginButton() {
    }
}
