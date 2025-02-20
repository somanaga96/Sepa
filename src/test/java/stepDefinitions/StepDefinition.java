package stepDefinitions;

import com.codoid.products.exception.FilloException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import locators.Login;
import utilities.BaseUtils;
import utilities.ExcelReader;
import utilities.XMLUtilities;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import static utilities.AllureUtils.allureScreenShot;
import static utilities.AllureUtils.takeScreenShot;
import static utilities.BaseUtils.*;
import static utilities.BaseUtils.configPropertyFilePath;

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
        allureScreenShot("Username and Password entered");

    }

    @And("user click on login button")
    public void userClickOnLoginButton() throws IOException {
        loginForms.login.click();
        takeScreenShot("Login Clicked");
        allureScreenShot("Login Clicked");
    }

    @When("user enter user name and password from the test data sheet")
    public void userEnterUserNameAndPasswordFromTheTestDataSheet() throws FilloException, IOException {
        String testCaseId = getScenarioContext().getScenarioContext("testCaseId").toString();
        String sheetName = getScenarioContext().getScenarioContext("sheetName").toString();
        Map<String, String> excelTestData = ExcelReader.readExcel(getPropertyValue(configPropertyFilePath, "ExcelDataFilePath"), sheetName, testCaseId);
        loginForms.enterUserNameAndPassword(excelTestData.get("username"), excelTestData.get("password"));
        System.out.println("test :" + testCaseId + sheetName);
        XMLUtilities.updateXMLTemplate(testCaseId, sheetName);
    }

    @Then("user update the value in Excel")
    public void userUpdateTheValueInExcel() throws FilloException {
        LocalDate time = LocalDate.now();
        String testCaseId = getScenarioContext().getScenarioContext("testCaseId").toString();
        String sheetName = getScenarioContext().getScenarioContext("sheetName").toString();
        ExcelReader.updateValueUsingHeaderInExcel(getPropertyValue(configPropertyFilePath, "ExcelDataFilePath"), sheetName, "time", testCaseId, time.toString());
    }
}
