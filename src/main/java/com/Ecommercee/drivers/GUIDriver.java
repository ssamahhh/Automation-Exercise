package com.Ecommercee.drivers;

import com.Ecommercee.utiles.Validations.Validation;
import com.Ecommercee.utiles.Validations.Verification;
import com.Ecommercee.utiles.actions.AlertActions;
import com.Ecommercee.utiles.actions.BrowserActions;
import com.Ecommercee.utiles.actions.ElementActions;
import com.Ecommercee.utiles.actions.FrameActions;
import com.Ecommercee.utiles.dataReader.PropertyReader;
import com.Ecommercee.utiles.logs.logsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class GUIDriver {
    private final String browser= PropertyReader.getProperty("browserType");
    private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public GUIDriver() {

        String browser = PropertyReader.getProperty("browserType");

        if (browser == null || browser.isEmpty()) {
            throw new RuntimeException("browserType is missing in properties file");
        }

        BrowserType browserType = BrowserType.valueOf(browser.toUpperCase());

        logsManager.info("Initializing driver for browser: " + browser);

        AbstractDriver abstractDriver = browserType.getDriverFactory();

        WebDriver driver = ThreadGuard.protect(abstractDriver.creatDriver());

        driverThreadLocal.set(driver);
    }

    public ElementActions element() {
        return new ElementActions(get());
    }

    public BrowserActions browser() {
        return new BrowserActions(get());
    }

    public FrameActions frame() {
        return new FrameActions(get());
    }

    public AlertActions alert() {
        return new AlertActions(get());
    }

    public Validation validate() {
        return new Validation(get());
    }

    public Verification HardValidate() {
        return new Verification(get());
    }

    public WebDriver get(){
        return driverThreadLocal.get();
    }

    public void quitDriver(){
        driverThreadLocal.get().quit();
    }

}
