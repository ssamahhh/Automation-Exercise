package com.Ecommercee.utiles.actions;

import com.Ecommercee.utiles.logs.logsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class BrowserActions {
    private final WebDriver driver;
    public BrowserActions(WebDriver driver){
        this.driver=driver;
    }

    public void maximizeWindow(String url){
        driver.manage().window().maximize();
    }

    public String getCurrentUrl(){
        String url= driver.getCurrentUrl();
        logsManager.info("Current URL: "+ url);
        return url;
    }

    //navigate to
    public void navigateTo(String url){
        driver.navigate().to(url);
        logsManager.info("Navigating to URL: "+ url);
    }

    //refresh page
    public void refreshPage(){
        driver.navigate().refresh();
    }

    //close the current window
    public void closeCurrentWindow(){
        driver.close();
    }

    public void OpenNewWindow(){
        driver.switchTo().newWindow(WindowType.WINDOW);
    }

}
