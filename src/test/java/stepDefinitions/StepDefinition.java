package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import locators.Login;
import utilities.BaseUtils;

import java.io.IOException;

import static utilities.AllureUtils.allureScreenShot;
import static utilities.AllureUtils.takeScreenShot;
import static utilities.BaseUtils.driver;

public class StepDefinition {
    Login loginForms = new Login(driver);

    @Given("user on Login page")
    public void userOnLoginPage() throws IOException {
        driver.get("https://www.saucedemo.com/");
        allureScreenShot("home page");
        takeScreenShot("Homepage");
    }

    @When("user enter {string} and {string}")
    public void userEnterAnd(String username, String password) throws IOException {
        System.out.println("pass :" + password);
        loginForms.enterUserNameAndPassword(username, password);
        takeScreenShot("Username and Password entered");
    }

    @And("user click on login button")
    public void userClickOnLoginButton() {
    }
}
