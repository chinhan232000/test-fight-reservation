package Utilities;

import Utilities.Utility;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

import static Common.GlobalVariables.*;


public class WebDriverUtils extends Utility {

    public static void maximizeWindow() {
        try {
            Utility.getDriver().manage().window().maximize();
        } catch (Exception e) {
            Utility.log4j.error("maximizeWindow method - ERROR: ", e);
        }
    }

    public static String getWindowHandle(WebDriver driver) {
        //get all the window handles after the popup window appears
        Set<String> afterPopup = driver.getWindowHandles();
        Iterator<String> iterator = afterPopup.iterator();
        while (iterator.hasNext()) {
            Utility.subWindowHandler = iterator.next();
        }

        return Utility.subWindowHandler;
    }

    public static void switchToWindowHandle() {
        try {
            String popupWindowHandle = getWindowHandle(Utility.getDriver());
            Utility.getDriver().switchTo().window(popupWindowHandle);
            maximizeWindow();
        } catch (Exception e) {
            Utility.log4j.error("switchToWindowHandle method - ERROR: ", e);
        }
    }

    public static void navigateToPage(ExtentTest logTest, String url) {
        try {
            TestReporter.logInfo(logTest, "Navigate to: " + url);
            Utility.log4j.info("navigateToPage method start ...");

            switchToWindowHandle();
            Utility.getDriver().navigate().to(url);

            Utility.log4j.info("navigateToPage method start ...");
            WebDriverUtils.waitForPageLoaded();
        } catch (Exception e) {
            Utility.log4j.error("navigateToTestSite method - ERROR: ", e);
            TestReporter.logException(logTest, "navigateToTestSite method - ERROR: ", e);
        }
    }

    public static void waitForPageLoaded() {
        Wait<WebDriver> wait;
        wait = new WebDriverWait(Utility.getDriver(), WAIT_TIME_60);
        try {
            //Wait for HTML load
            wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver) {
                    boolean readyState = ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    boolean activeJQuery = ((JavascriptExecutor) driver).executeScript("if (typeof jQuery != 'undefined') {return jQuery.active == 0; } else { return true; }").equals(true);
                    return readyState && activeJQuery;
                }
            });

        } catch (Exception e) {
            Utility.log4j.error("waitForPageloaded - ERROR: ", e);
        }
    }

}
