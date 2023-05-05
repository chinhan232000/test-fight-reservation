package TestCases;

import static Common.GlobalVariables.PAGE_URL;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.FlightOrder.HomePage;
import PageObjects.FlightOrder.LoginPage;
import Utilities.TestReporter;
import Utilities.Utility;
import Utilities.WebDriverUtils;

public class LoginTest extends BaseTest {
    @Test(description = "User can log into Flight Order with valid username and password")
    public void TC01() {
		try {
			logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Flight Order");
			WebDriverUtils.navigateToPage(logStep, PAGE_URL);
	
			logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login with valid account");
			LoginPage loginPage = new LoginPage();
			loginPage.emailInput.sendKeys("admin@gmail.com");
			loginPage.passwordInput.sendKeys("admin");
			loginPage.submitButton.click();
	
			logStep = TestReporter.logStepInfo(logMethod, "Step #3: Verify that login successfully");
			HomePage homePage = new HomePage();
			String text = homePage.welcomeName.getText();
			Assert.assertEquals(text, "admin","Login unsuccessfully");
		} catch(Exception e) {
			TestReporter.logException(logMethod, "TC01", e);
		} finally {
			Utility.getDriver().quit();
		}
    }

	@Test(description = "User can't log into Flight Order with invalid username and password")
    public void TC02() {
		try {
			logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Flight Order");
			WebDriverUtils.navigateToPage(logStep, PAGE_URL);
	
			logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login with invalid account");
			LoginPage loginPage = new LoginPage();
			loginPage.emailInput.sendKeys("fake@gmail.com");
			loginPage.passwordInput.sendKeys("fake");
			loginPage.submitButton.click();
	
			logStep = TestReporter.logStepInfo(logMethod, "Step #3: Verify that login fail");
			String text = loginPage.alertMessage.getText();
			Assert.assertEquals(text, "Email is not exist");
		} catch(Exception e) {
			TestReporter.logException(logMethod, "TC02", e);
		} finally {
			Utility.getDriver().quit();
		}
    }

	@Test(description = "User can't log into Flight Order with invalid password")
    public void TC03() {
		try {
			logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Flight Order");
			WebDriverUtils.navigateToPage(logStep, PAGE_URL);
	
			logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login with invalid password");
			LoginPage loginPage = new LoginPage();
			loginPage.emailInput.sendKeys("admin@gmail.com");
			loginPage.passwordInput.sendKeys("fake");
			loginPage.submitButton.click();
	
			logStep = TestReporter.logStepInfo(logMethod, "Step #3: Verify that login fail");
			String text = loginPage.alertMessage.getText();
			Assert.assertEquals(text, "Password is not correct");
		} catch(Exception e) {
			TestReporter.logException(logMethod, "TC03", e);
		} finally {
			Utility.getDriver().quit();
		}
    }
}
