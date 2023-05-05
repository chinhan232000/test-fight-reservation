package TestCases;

import static Common.GlobalVariables.PAGE_URL;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.FlightOrder.HomePage;
import PageObjects.FlightOrder.LoginPage;
import PageObjects.FlightOrder.PassengersPage;
import Utilities.TestReporter;
import Utilities.Utility;
import Utilities.WebDriverUtils;

public class PassengerTest extends BaseTest {
  @Test(description = "Login and access to Passengers page")
  public void TC01() {
		try {
			logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Flight Order");
			WebDriverUtils.navigateToPage(logStep, PAGE_URL);
	
			logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login with valid account");
			LoginPage loginPage = new LoginPage();
			loginPage.emailInput.sendKeys("admin@gmail.com");
			loginPage.passwordInput.sendKeys("admin");
			loginPage.submitButton.click();
	
			logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Passengers page");
			HomePage homePage = new HomePage();
			WebElement passengerNavItem = homePage.getNavItem("/passenger");
      passengerNavItem.click();

      logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify Passenger page");
      PassengersPage passengersPage = new PassengersPage();
      String text = passengersPage.heading.getText();
			Assert.assertEquals(text, "Passengers");
		} catch(Exception e) {
			TestReporter.logException(logMethod, "TC01", e);
		} finally {
			Utility.getDriver().quit();
		}
  }

	@Test(description = "Search passenger by name")
  public void TC02() {
		try {
			logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Flight Order");
			WebDriverUtils.navigateToPage(logStep, PAGE_URL);
	
			logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login with valid account");
			LoginPage loginPage = new LoginPage();
			loginPage.emailInput.sendKeys("admin@gmail.com");
			loginPage.passwordInput.sendKeys("admin");
			loginPage.submitButton.click();
	
			logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Passengers page");
			HomePage homePage = new HomePage();
			WebElement passengerNavItem = homePage.getNavItem("/passenger");
      passengerNavItem.click();

      logStep = TestReporter.logStepInfo(logMethod, "Step #4: Fill search input and search");
      PassengersPage passengersPage = new PassengersPage();
      passengersPage.searchInput.sendKeys("Smith");
      passengersPage.searchButton.click();

      logStep = TestReporter.logStepInfo(logMethod, "Step #5: Validate first result");
      WebElement passenger = passengersPage.getPassengerByOrder(1);
      String text = passengersPage.getPassengerName(passenger);
			Assert.assertEquals(text, "William Smith");
		} catch(Exception e) {
			TestReporter.logException(logMethod, "TC02", e);
		} finally {
			Utility.getDriver().quit();
		}
  }

	@Test(description = "User show edit Passenger info on modal")
  public void TC03() {
		try {
			logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Flight Order");
			WebDriverUtils.navigateToPage(logStep, PAGE_URL);
	
			logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login with valid account");
			LoginPage loginPage = new LoginPage();
			loginPage.emailInput.sendKeys("admin@gmail.com");
			loginPage.passwordInput.sendKeys("admin");
			loginPage.submitButton.click();
	
			logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Passengers page");
			HomePage homePage = new HomePage();
			WebElement passengerNavItem = homePage.getNavItem("/passenger");
      passengerNavItem.click();

      logStep = TestReporter.logStepInfo(logMethod, "Step #4: Fill search input and search");
      PassengersPage passengersPage = new PassengersPage();
      passengersPage.searchInput.sendKeys("Smith");
      passengersPage.searchButton.click();

      logStep = TestReporter.logStepInfo(logMethod, "Step #5: Show edit modal");
      WebElement passenger = passengersPage.getPassengerByOrder(1);
      passengersPage.getEditButton(passenger).click();

      logStep = TestReporter.logStepInfo(logMethod, "Step #5: Validate modal info");
      synchronized (passengersPage) {
        passengersPage.wait(1000);
      }
			Assert.assertEquals(passengersPage.modalHeader.getText(), "Update Passenger");
			Assert.assertEquals(passengersPage.modalNameInput.getAttribute("value"), "William Smith");
			Assert.assertEquals(passengersPage.modalEmailInput.getAttribute("value"), "smith@gmail.com");
		} catch(Exception e) {
			TestReporter.logException(logMethod, "TC03", e);
		} finally {
			Utility.getDriver().quit();
		}
  }
}
