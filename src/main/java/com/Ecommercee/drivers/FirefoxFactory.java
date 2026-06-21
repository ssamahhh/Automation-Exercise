package com.Ecommercee.drivers;

import com.Ecommercee.utiles.dataReader.PropertyReader;
import com.Ecommercee.utiles.logs.logsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class FirefoxFactory extends AbstractDriver{

    private FirefoxOptions getOptions(){
        FirefoxOptions options=new FirefoxOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--start-maximized");
        if(PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless")
                ||PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")){

            options.addArguments("--headless=new");
        }

        return options;
    }
    @Override
    public WebDriver creatDriver() {
        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Local") || PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless"))
            return new FirefoxDriver(getOptions());
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
