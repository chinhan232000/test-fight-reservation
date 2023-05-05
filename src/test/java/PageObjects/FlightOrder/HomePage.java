package PageObjects.FlightOrder;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @FindBy(xpath = "//span[@class='fs-4']")
    public WebElement welcomeName;

    public HomePage() {
        PageFactory.initElements(Utility.getDriver(), this);
    }

    public WebElement getNavItem(String path) {
		By byNavItem = By.xpath("//li/a[@href='" + path + "']");
      	return Utility.getDriver().findElement(byNavItem);
    }
}
