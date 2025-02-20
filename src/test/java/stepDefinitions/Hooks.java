package stepDefinitions;

import com.codoid.products.exception.FilloException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import utilities.BaseUtils;
import utilities.ExcelReader;

public class Hooks extends BaseUtils {


    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Scenario name :" + scenario.getName());
        String sheetName = getParameterValue(scenario, "sheetName");
        String testCaseId = getParameterValue(scenario, "testCaseId");
        String customizedName = String.format("Login to Swag Labs - %s - %s", sheetName, testCaseId);
        Allure.getLifecycle().updateTestCase(test -> test.setName(customizedName));
        createWebDriver(scenario);
    }

    private String getParameterValue(Scenario scenario, String paramName) {
        // Extracts the parameter value from the scenario outline
        return scenario.getSourceTagNames()
                .stream()
                .filter(tag -> tag.startsWith("@" + paramName + "="))
                .map(tag -> tag.split("=")[1])
                .findFirst()
                .orElse("Unknown");
    }

    @After
    public void tearDown() throws FilloException {
        String testCaseId = getScenarioContext().getScenarioContext("testCaseId").toString();
        String sheetName = getScenarioContext().getScenarioContext("sheetName").toString();
        ExcelReader.updateValueUsingHeaderInExcel(getPropertyValue(configPropertyFilePath, "ExcelDataFilePath"), sheetName, "ScreenShotPath", testCaseId, scenarioID);
        if (driver != null) {
            driver.quit();
        }
    }
}
