package PageObjects.FlightOrder;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PassengersPage extends BasePage {
	public static final String PAGE_URL = "http://localhost:3000/passenger";

	@FindBy(xpath = "//h1[@class='display-2 text-center mt-3']")
    public WebElement heading;
    @FindBy(xpath = "//button[text()='Create Passenger']")
    public WebElement createPassengerButton;
    @FindBy(xpath = "//input[@placeholder='Search']")
    public WebElement searchInput;
    @FindBy(xpath = "//button[text()='Search']")
    public WebElement searchButton;
	@FindBy(xpath = "//div[@id='modal']")
    public WebElement modal;
	@FindBy(xpath = "//h5[@class='modal-title']")
    public WebElement modalHeader;
	@FindBy(xpath = "//input[@name='name']")
    public WebElement modalNameInput;
	@FindBy(xpath = "//input[@name='email']")
    public WebElement modalEmailInput;
	@FindBy(xpath = "//input[@name='phone']")
    public WebElement modalPhoneInput;
	@FindBy(xpath = "//input[@name='address']")
    public WebElement modalAddressInput;

    public PassengersPage() {
        PageFactory.initElements(Utility.getDriver(), this);
    }

    public WebElement getPassengerByOrder(int order) {
		By valueInTable = By.xpath("//tbody/tr[" + order + "]");
		return Utility.getDriver().findElement(valueInTable);
  	}

	public String getPassengerName(WebElement passengerElement) {
        By item = By.xpath("//td[2]");
        return passengerElement.findElement(item).getText();
    }

    public WebElement getEditButton(WebElement passengerElement) {
        By editButton = By.xpath("//button[@class='btn btn-warning']");
        return passengerElement.findElement(editButton);
    }

    public WebElement getDeleteButton(WebElement passengerElement) {
        By deleteButton = By.xpath("//button[@class='btn btn-danger']");
        return passengerElement.findElement(deleteButton);
    }
}
