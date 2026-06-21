package com.Ecommercee.drivers;

import com.Ecommercee.utiles.dataReader.PropertyReader;
import com.Ecommercee.utiles.logs.logsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URI;

public class SafariFactory extends AbstractDriver{

    private SafariOptions getOptions(){
        SafariOptions options=new SafariOptions();
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        return options;
    }
    @Override
    public WebDriver creatDriver() {
        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Local") || PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless"))
            return new SafariDriver(getOptions());
        else if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")) {
            try {
                return new RemoteWebDriver(
                        new URI("http://" + ":" + PropertyReader.getProperty("remoteHost") + ":" + PropertyReader.getProperty("remotePort") + "/wd/hub").toURL(),
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
