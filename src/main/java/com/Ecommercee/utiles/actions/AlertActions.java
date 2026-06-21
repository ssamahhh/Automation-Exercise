package com.Ecommercee.utiles.actions;

import com.Ecommercee.WaitManager;
import com.Ecommercee.utiles.logs.logsManager;
import org.openqa.selenium.WebDriver;

public class AlertActions {
    private final WebDriver driver;
    private final WaitManager waitManager;

    public AlertActions(WebDriver driver){
        this.driver=driver;
        this.waitManager=new WaitManager(driver);
    }

    public void acceptAlert(){
        waitManager.fluentWait().until(d->
        {
            try{
                d.switchTo().alert().accept();
                return true;
            }
            catch(Exception e)
            {
                logsManager.error("Failed to accept alert", e.getMessage());
                return false;
            }
        });
    }

    public void dismissAlert(){
        waitManager.fluentWait().until(d->{
            try{
                d.switchTo().alert().dismiss();
                return true;
            }
            catch(Exception e){
                logsManager.error("Failed to dismiss alert", e.getMessage());
                return false;
            }
        });
    }

    public String getAlert(){
        return waitManager.fluentWait().until(d->{
            try{
                String text=d.switchTo().alert().getText();
                return !text.isEmpty() ? text:null;
            }
            catch(Exception e){
                logsManager.error("Failed to get alert text", e.getMessage());
                return null;
            }
        });
    }

    public void typeAlert(String txt){
        waitManager.fluentWait().until(d->{
            try{
                d.switchTo().alert().sendKeys(txt);
                return true;
            }
            catch(Exception e){
                logsManager.error("Failed to type in alert", e.getMessage());
                return false;
            }
        });
    }
}
