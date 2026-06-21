package com.Ecommercee.utiles.actions;

import com.Ecommercee.WaitManager;
import com.Ecommercee.utiles.logs.logsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FrameActions {
    private final WebDriver driver;
    private final WaitManager waitManager;

    public FrameActions(WebDriver driver){
        this.driver=driver;
        this.waitManager=new WaitManager(driver);
    }

    public void SwitchToFrameByIndex(int index){
        waitManager.fluentWait().until(d->{
                    try{
                        d.switchTo().frame(index);
                        logsManager.info("Switched to frame by index:"+ index);
                        return true;
                    }
                    catch(Exception e){
                        return false;
                    }
                });
    }

    public void SwitchToFrameByNameOrId(String nameOrId){
        waitManager.fluentWait().until(d->{
            try{
                d.switchTo().frame(nameOrId);
                logsManager.info("Switched to frame by name or id:"+ nameOrId);
                return true;
            }
            catch(Exception e){
                return false;
            }
        });
    }

    public void SwitchToFrameByWebElement(By framelocator){
        waitManager.fluentWait().until(d->{
            try{
                d.switchTo().frame(d.findElement(framelocator));
                logsManager.info("Switched to frame by web element:"+ framelocator);
                return true;
            }
            catch(Exception e){
                return false;
            }
        });
    }

    public void switchToDefaultContent(){
        waitManager.fluentWait().until(d->{
            try{
                d.switchTo().defaultContent();
                logsManager.info("Switched to default content");
                return true;
            }
            catch(Exception e){
                return false;
            }
        });
    }
}
