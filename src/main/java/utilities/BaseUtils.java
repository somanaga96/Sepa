package utilities;

import com.codoid.products.exception.FilloException;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.internal.shadowed.jackson.databind.annotation.JsonAppend;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

import static utilities.Constants.CONFIGURATION_DIRECTORY;
import static utilities.GeneralUtilities.getUKCurrentDate;

public class BaseUtils implements Constants {
    public static WebDriver driver;
    public static Properties configPropertyFilePath = getProps(CONFIGURATION_DIRECTORY, "config");
    public static String runID;
    public static String scenarioID;
    public static Scenario scenario;
    private static ScenarioContext scenarioContext;

    public BaseUtils() {
        scenarioContext = new ScenarioContext();  // Ensure it's initialized
    }

    public static ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

    public void createWebDriver(Scenario scenario) {
        BaseUtils.scenario = scenario;
//        System.out.println("scenario :"+scenario.getName());
//        System.out.println("scenario :"+scenario.getId());
//        System.out.println("scenario :"+scenario.getLine());
//        System.out.println("scenario :"+scenario.getStatus());
//        System.out.println("scenario :"+scenario.getSourceTagNames());
//        System.out.println("scenario :"+scenario.getClass());
//        System.setProperty("browser", "chrome");
        String browserName = "chrome";
        switch (browserName) {
            case "chrome":
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case "edge":
                driver = new EdgeDriver();
                driver.manage().window().maximize();
        }

    }

    public static void Click(WebElement element) {
        waitElementToBeClickable(element, 30);
        element.click();
    }

    public static void EnterText(WebElement element, String text) {
        waitElementToBeVisible(element, 30);
        element.sendKeys(text);
    }

    public static void waitElementToBeClickable(WebElement element, long timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    public static void waitElementToBeVisible(WebElement element, long timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            Assert.fail();
        }
    }


    public void createScreenShotFolder(String testCaseId, String sheetName) throws FilloException, IOException {
        String screenShotPath = ExcelReader.readExcel(getPropertyValue(configPropertyFilePath, "ExcelDataFilePath"), sheetName, testCaseId).get("ScreenShotPath");
        System.out.println("ScreenShotPath :" + screenShotPath);
        final File htmlTemplateFile;
        File newHtmlFile;
        if (screenShotPath.isEmpty()) {
            runID = getUKCurrentDate("dd_MM_yyyy_HH_mm_ss", "BST");
            scenarioID = testCaseId + "-" + runID;
            htmlTemplateFile = new File(Constants.SCREEN_SHOT_FOLDER_PATH + "screenshot_template.html");
            newHtmlFile = new File(Constants.SCREEN_SHOT_FOLDER_PATH + scenarioID + "/" + scenarioID + ".html");
        } else {
            scenarioID = screenShotPath;
            runID = getUKCurrentDate("dd_MM_yyyy_HH_mm_ss", "BST");
            htmlTemplateFile = new File(Constants.SCREEN_SHOT_FOLDER_PATH + "screenshot_template.html");
            newHtmlFile = new File(Constants.SCREEN_SHOT_FOLDER_PATH + scenarioID + "/" +testCaseId+"-"+ runID + ".html");
        }
        String newHtmlStr = FileUtils.readFileToString(htmlTemplateFile, "UTF-8");
        newHtmlStr = newHtmlStr.replace("$testCaseId$", testCaseId);
        newHtmlStr = newHtmlStr.replace("$ScenarioName$", scenario.getName());
        newHtmlStr = newHtmlStr.replace("$Execution Start Time$", runID);
//        newHtmlStr = newHtmlStr.replace("$Execution Start Time$", runID.replace("_", " "));
        FileUtils.writeStringToFile(newHtmlFile, newHtmlStr, "UTF-8");
    }

    public static String getPropertyValue(Properties properties, String key) {
        return properties.getProperty(key);
    }

    public static Properties getProps(final String FILE_PATH, final String FILE_NAME) {
        Properties properties = new Properties();
        try {
            File file = new File(FILE_PATH, FILE_NAME + ".properties");
            System.out.println("filepath :" + FILE_PATH + FILE_NAME);
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            System.out.println("File exceptions :" + e.getMessage());
        }
        return properties;
    }

    public void setPropertyValue(Properties properties, String key, String value) {
        properties.setProperty(key, value);
    }
}
