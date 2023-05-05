package Utilities;

import Utilities.webDrivers.DriverSetup;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.SkipException;

import java.io.File;

import static Utilities.Utility.getDriver;

public class TestReporter {
    public static void logInfo(ExtentTest logTest, String description) {
        logTest.info(description);
    }

    public static void logFail(ExtentTest logTest, String description) {
        try {
            captureScreenshot(logTest, "FAILED screenshot: ", "fail-");
            throw new SkipException(description);
        } catch (SkipException e) {
            logTest.fail(MarkupHelper.createLabel(description + "</br>" + Utility.getStackTrace(e.getStackTrace()), ExtentColor.RED));
            Assert.fail(description);
        }
    }

    public static void logSkip(ExtentTest logtest, String description) {
        logtest.skip(MarkupHelper.createLabel(description, ExtentColor.GREY));
    }

    public static void logPass(ExtentTest logtest, String description) {
        logtest.pass(MarkupHelper.createLabel(description, ExtentColor.GREEN));
    }

    public static void logException(ExtentTest logTest, String description, Exception ex) {
        try {
            captureScreenshot(logTest, "ERROR screenshot: ", "error-");
            throw new SkipException(description);
        } catch (SkipException e) {
            String msg = description + "</br>" + ex.toString() + "</br>" + Utility.getStackTrace(ex.getStackTrace());
            logTest.fail(MarkupHelper.createLabel(msg, ExtentColor.ORANGE));
            Assert.fail(description);
        }
    }

    public static ExtentTest logStepInfo(ExtentTest logTest, String description, Object... args) {
        return logTest.createNode(description);
    }

    public static ExtentTest createNodeForExtentReport(ExtentTest parentTest, String description) {
        return parentTest.createNode(description);
    }

    public static ExtentTest createTestForExtentReport(ExtentReports report, String description) {
        return report.createTest(description);
    }

    public static void captureScreenshot(ExtentTest logTest, String detail, String screenShotName) {
        try {
            screenShotName = screenShotName + DataFaker.generateTimeStampString("yyyy-MM-dd-HH-mm-ss") + ".png";

            TakesScreenshot ts = (TakesScreenshot) getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            String dest = Utility.reportLocation + screenShotName; //Storing the image in reportLocation
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);

            //Add current url to report
            if (getDriver().getCurrentUrl() != null) logTest.info("Page url: " + getDriver().getCurrentUrl());

            //Add screenshot to report
            String screenShotLink = "<a href=\"" + screenShotName + "\"" + screenShotName + "</a>";
            if (logTest.getStatus() == Status.FAIL) {
                logTest.fail(detail + screenShotLink).addScreenCaptureFromPath(screenShotName);
            } else logTest.pass(detail + screenShotLink).addScreenCaptureFromPath(screenShotName);

        } catch (Exception e) {
            Utility.log4j.info("An error occurred when capturing screen shot: ", e);
        }
    }
}
