package utilities;

import io.cucumber.java.Scenario;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseUtils {
    public static WebDriver driver;

    public void createWebDriver(Scenario scenario) {
//        System.out.println("scenario :"+scenario.getName());
//        System.out.println("scenario :"+scenario.getId());
//        System.out.println("scenario :"+scenario.getLine());
//        System.out.println("scenario :"+scenario.getStatus());
//        System.out.println("scenario :"+scenario.getSourceTagNames());
//        System.out.println("scenario :"+scenario.getClass());
//        System.setProperty("browser", "chrome");
        String browserName = "edge";
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
}
