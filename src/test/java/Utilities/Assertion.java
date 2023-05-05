package Utilities;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebElement;

import static Utilities.Utility.log4j;

public class Assertion {
    public static void verifyActualAndExpected(ExtentTest logTest, String actual, String expected) {
        try {
            log4j.info("Actual" + actual);
            log4j.info("Expected" + expected);
            if (actual.trim().equalsIgnoreCase(expected.trim())) {
                TestReporter.logPass(logTest, "Actual: " + actual + "</br>Expected: " + expected + "</br>");
            } else
                TestReporter.logFail(logTest, "Actual: " + actual + "</br>Expected: " + expected + "</br>");
        } catch (Exception e) {
            log4j.error("verifyActualAndExpected method - ERROR: ", e);
            TestReporter.logException(logTest,"verifyActualAndExpected method - ERROR: ", e);
        }
    }
}
