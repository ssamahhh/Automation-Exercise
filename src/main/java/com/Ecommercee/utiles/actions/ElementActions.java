package com.Ecommercee.utiles.actions;

import com.Ecommercee.WaitManager;
import com.Ecommercee.utiles.logs.logsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Collection;
import java.util.List;

public class ElementActions {
    private final WebDriver driver;
    private WaitManager waitManager;

    public ElementActions(WebDriver driver){
        this.driver=driver;
        this.waitManager =new WaitManager(driver);
    }

    public void click(By locator){
            waitManager.fluentWait().until(d->{
                try {
                    WebElement element=d.findElement(locator);
                    scrollToElement(locator);
                    element.click();
                    logsManager.info("Clicked on element: "+ locator);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            });
        }

        public void type(By locator,String txt){
            waitManager.fluentWait().until(d->{
                try {
                    WebElement element=d.findElement(locator);
                    scrollToElement(locator);
                    element.clear();
                    element.sendKeys(txt);
                    logsManager.info("Typed text '"+ txt +"' into element: "+ locator);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            });
        }

        //gettext
    public String getText(By locator){
        return waitManager.fluentWait().until(d->{
            try {
                WebElement element=d.findElement(locator);
                scrollToElement(locator);
                String msg=element.getText();
                logsManager.info("Got text '"+ msg +"' from element: "+ locator);
                return !msg.isEmpty() ? msg:null;

            } catch (Exception e) {
                return null;
            }
        });
    }

    //upload file
    public void uploadFile(By locator, String filePath){
        String fileAbolute=System.getProperty("user.dir")+ File.separator+filePath;
        waitManager.fluentWait().until(d->{
            try {
                WebElement element=d.findElement(locator);
                scrollToElement(locator);
                element.clear();
                element.sendKeys(fileAbolute);
                logsManager.info("Uploaded file '"+ fileAbolute +"' using element: "+ locator);
                return true;
            } catch (Exception e) {
                return false;
            }
        });

    }

    //find element
    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    //function to scroll to an element using js

    public void scrollToElement(By locator){
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({behavior:'auto', block:'center', inline:'center'});",
                        findElement(locator)
                );
    }

    public int getElementsCount(By locator){
        return driver.findElements(locator).size();
    }

    //dropdown
    public void selectFromDropdown(By locator, String value){
        waitManager.fluentWait().until(d->{
            try {
                WebElement element=d.findElement(locator);
                scrollToElement(locator);
                Select select=new Select(element);
                select.selectByVisibleText(value);
                logsManager.info("Selected option '"+ value +"' from dropdown: "+ locator);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }
}
