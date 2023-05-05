package PageObjects.FlightOrder;

import Utilities.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailInput;
    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordInput;
    @FindBy(xpath = "//button[text()='Submit']")
    public WebElement submitButton;
    @FindBy(xpath = "//div[@class='alert alert-danger']")
    public WebElement alertMessage;

    public LoginPage() {
        PageFactory.initElements(Utility.getDriver(), this);
    }
}
