package PageObjects.FlightOrder;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Common.GlobalVariables;
import Utilities.Utility;

public class BasePage extends GlobalVariables {

    protected WebElement navigationBar(String option) {
        By optionNarBar = By.xpath("//li/a[@href='" + option + "']");
        return Utility.getDriver().findElement(optionNarBar);
    }

    protected void scrollToElement(WebElement element) {
        Actions actions = new Actions(Utility.getDriver());
        actions.scrollToElement(element);
        actions.perform();
    }

    protected void alertAccept(){
        Alert alert = Utility.getDriver().switchTo().alert();
        alert.accept();
    }

    protected void clickLinkText(String name) {
        By link = By.linkText(name);
        Utility.getDriver().findElement(link).click();
    }
    protected void verifyDdlValues(WebElement element, String value) {
        Select dropDown = new Select(element);
        Assert.assertEquals(value, dropDown.getFirstSelectedOption().getText());
    }
    protected void verifyBookedTicket(WebElement element, String value) {
        Assert.assertEquals(value, element.getText());
    }
}
