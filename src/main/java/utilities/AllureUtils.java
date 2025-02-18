package utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class AllureUtils extends BaseUtils {
    public static void allureScreenShot(String name) {
        // Capture screenshot as bytes
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Add the screenshot as an attachment in the Allure report
        Allure.addAttachment(name, "image/png", new ByteArrayInputStream(screenshot), ".png");
    }

    public static void takeScreenShot(String message) throws IOException {
        String testCaseId = getScenarioContext().getScenarioContext("testCaseId").toString();
        String sheetName = getScenarioContext().getScenarioContext("sheetName").toString();
        String screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        File newHtmlFile = new File(Constants.SCREEN_SHOT_FOLDER_PATH + scenarioID + "/" + testCaseId + "-" + runID + ".html");
        String htmlString = FileUtils.readFileToString(newHtmlFile, "UTF-8");
        String newDiv = "<div> <h3> " + message + "</h3><img src=\"data:image/png;base64, " + screenshotAs + "\" ></div>--End--";
        htmlString = htmlString.replace("--End--", newDiv);
        FileUtils.writeStringToFile(newHtmlFile, htmlString, "UTF-8");
    }

}
