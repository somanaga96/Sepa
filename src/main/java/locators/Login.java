package locators;

import forms.LoginLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utilities.AllureUtils.allureScreenShot;

public class Login {
    private WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize elements
    }

    @FindBy(id = LoginLocators.USER_NAME)
    WebElement user_name;
    @FindBy(id = LoginLocators.PASSWORD)
    WebElement password;
    @FindBy(id = LoginLocators.LOGIN)
    WebElement login;

    public void enterUserNameAndPassword(String username, String pass) {
        System.out.println("check userj pass :" + username + "-" + pass);
//        driver.findElement(By.xpath(LoginLocators.USER_NAME)).sendKeys(username);
        user_name.sendKeys(username);
        password.sendKeys(pass);
        allureScreenShot("user name and password entered");
        login.click();
        allureScreenShot("login clicked");
    }
}
