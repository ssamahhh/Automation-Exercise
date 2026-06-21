package com.Ecommercee.drivers;

import com.Ecommercee.utiles.dataReader.PropertyReader;
import com.Ecommercee.utiles.logs.logsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class chromeFactory extends AbstractDriver {
    private ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless")
                || PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")) {

            options.addArguments("--headless=new");
        }

        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return options;
    }

    @Override
    public WebDriver creatDriver() {
        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Local") || PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless"))
            return new ChromeDriver(getOptions());
        else if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")) {
            try {
                return new RemoteWebDriver(
                        new URI("http://" + PropertyReader.getProperty("remoteHost") + ":"
                                + PropertyReader.getProperty("remotePort") + "/wd/hub").toURL(),
                        getOptions());
            } catch (Exception e) {
                logsManager.error("Failed to create remote WebDriver: " + e.getMessage());
                throw new RuntimeException("Failed to creat RemoteWebDriver", e);
            }

        } else {
            logsManager.error("Invalid execution type: " + PropertyReader.getProperty("executionType"));
            throw new IllegalArgumentException("Invalid execution type: " + PropertyReader.getProperty("executionType"));
        }
    }
}
