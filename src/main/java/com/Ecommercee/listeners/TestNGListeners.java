package com.Ecommercee.listeners;

import com.Ecommercee.utiles.FilesUtils;
import com.Ecommercee.utiles.Validations.Validation;
import com.Ecommercee.utiles.allurereport.AllureAttachmentManager;
import com.Ecommercee.utiles.allurereport.AllureConstants;
import com.Ecommercee.utiles.allurereport.AllureEnvironmentManager;
import com.Ecommercee.utiles.allurereport.AllureReportGenerator;
import com.Ecommercee.drivers.WebDriverProvider;
import com.Ecommercee.utiles.logs.logsManager;
import com.Ecommercee.utiles.media.ScreenRecorderManager;
import com.Ecommercee.utiles.media.ScreenShotManager;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;

import static com.Ecommercee.utiles.dataReader.PropertyReader.loadProperties;

public class TestNGListeners implements IExecutionListener, IInvokedMethodListener, ITestListener, ISuiteListener {
    File screenshots = new File("test-output/screenshots");
    File recordings = new File("test-output/recordings");
    File logs = new File("test-output/Logs");

    @Override
    public void onExecutionStart() {
        logsManager.info("Test Execution started");


        cleanTestOutputDirectories();
        logsManager.info("Directories cleaned");

        createTestOutputDirectories();
        logsManager.info("Directories created");

        loadProperties();
        logsManager.info("Properties loaded");


        AllureEnvironmentManager.setEnvironmentVariables();
        logsManager.info("Allure environment set");
    }


    @Override
    public void onExecutionFinish() {
        AllureReportGenerator.generateReports(false);
        AllureReportGenerator.copyHistory();
        logsManager.info("History copied");
        AllureReportGenerator.generateReports(true);
        String newFileName = AllureReportGenerator.renameReport();
        AllureReportGenerator.openReport(newFileName);
        logsManager.info("Test Execution Finished");
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            ScreenRecorderManager.startRecording();
            logsManager.info("Test Case " + testResult.getName() + " started");
        }
    }


    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        WebDriver driver = null;
        if (method.isTestMethod()) {
            ScreenRecorderManager.stopRecording(result.getName());
            Validation.assertAll();
            if (result.getInstance() instanceof WebDriverProvider provider)
                driver = provider.getWebDriver();
            switch (result.getStatus()) {
                case ITestResult.FAILURE -> ScreenShotManager.takeFullPageScreenShot(driver, "failed-" + result.getName());

                case ITestResult.SUCCESS -> ScreenShotManager.takeFullPageScreenShot(driver, "passed-" + result.getName());

                case ITestResult.SKIP -> ScreenShotManager.takeFullPageScreenShot(driver, "skipped-" + result.getName());

            }
            AllureAttachmentManager.attachLogs();
            AllureAttachmentManager.attachRecords(result.getName());
        } else if (method.isConfigurationMethod()) {
            // For Configuration Methods: Log Only
            switch (result.getStatus()) {
                case ITestResult.FAILURE -> logsManager.info("Configuration Method ", result.getName(), "failed");
                case ITestResult.SUCCESS -> logsManager.info("Configuration Method ", result.getName(), "passed");
                case ITestResult.SKIP -> logsManager.info("Configuration Method ", result.getName(), "skipped");
            }
        }
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        logsManager.info("Test case", result.getName(), "passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logsManager.info("Test case", result.getName(), "failed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logsManager.info("Test case", result.getName(), "skipped");
    }

    public void onFinish(ISuite suite) {

    }

    private void cleanTestOutputDirectories() {
        FilesUtils.cleanDirectory(AllureConstants.RESULTS_FOLDER.toFile());
        FilesUtils.cleanDirectory(screenshots);
        FilesUtils.cleanDirectory(recordings);
        FilesUtils.forceDelete(logs);
    }

    private void createTestOutputDirectories() {
        FilesUtils.createDirs(ScreenShotManager.SCREENSHOTS_PATHS);
        FilesUtils.createDirs(ScreenRecorderManager.RECORDINGS_PATH);
    }


}
