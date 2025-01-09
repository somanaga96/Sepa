package utilities;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

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
}
