package stepDefinitions;

import com.codoid.products.exception.FilloException;
import io.cucumber.java.en.Given;
import utilities.BaseUtils;

import java.io.IOException;

public class ScreenShotStepDefination extends BaseUtils {
    @Given("user create screenshot folder from {string} and {string}")
    public void userCreateScreenshotFolderFromAnd(String sheetName, String testCaseId) {
        try {
            createScreenShotFolder(testCaseId, sheetName);
        } catch (FilloException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getScenarioContext().setScenarioContext("sheetName", sheetName);
        getScenarioContext().setScenarioContext("testCaseId", testCaseId);
    }
}
