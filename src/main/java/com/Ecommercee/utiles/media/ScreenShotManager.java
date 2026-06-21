package com.Ecommercee.utiles.media;

import com.Ecommercee.utiles.TimeManager;
import com.Ecommercee.utiles.allurereport.AllureAttachmentManager;
import com.Ecommercee.utiles.logs.logsManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenShotManager {

    public static final String SCREENSHOTS_PATHS = "test-output/screenshots/";

    public static void takeFullPageScreenShot(WebDriver driver, String screenShotName) {

        try {
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File screenshotFile = new File(SCREENSHOTS_PATHS + screenShotName + "-" + TimeManager.getTimeStamp() + ".png");

            FileUtils.copyFile(screenshotSrc, screenshotFile);

            AllureAttachmentManager.attachScreenshot(screenShotName, screenshotFile.getAbsolutePath());

            logsManager.info("Capture Screenshot Succeeded");

        } catch (Exception e) {
            logsManager.error("Failed to Capture Screenshot" + e.getMessage());
        }
    }

    //take screenshot for specific element
    public static void takeElementScreenShot(WebDriver driver, By locator) {

        try {
            String arianame=driver.findElement(locator).getAccessibleName();
            File screenshotSrc =
                    driver.findElement(locator).getScreenshotAs(OutputType.FILE);

            File screenshotFile =
                    new File(SCREENSHOTS_PATHS + arianame + "-"
                            + TimeManager.getTimeStamp() + ".png");

            FileUtils.copyFile(screenshotSrc, screenshotFile);

            logsManager.info("Capture Screenshot Succeeded");

        } catch (Exception e) {
            logsManager.error("Failed to Capture Screenshot" + e.getMessage());
        }
    }
}
