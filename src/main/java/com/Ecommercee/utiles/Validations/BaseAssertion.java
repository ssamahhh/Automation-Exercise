package com.Ecommercee.utiles.Validations;

import com.Ecommercee.WaitManager;
import com.Ecommercee.utiles.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseAssertion {
    protected WebDriver driver;
    protected WaitManager waitManager;
    protected ElementActions elementActions;

    protected BaseAssertion(){

    }

    protected BaseAssertion(WebDriver driver){
        this.driver=driver;
        this.waitManager=new WaitManager(driver);
        this.elementActions=new ElementActions(driver);
    }

    protected abstract void assertTrue(boolean conditions,String message );
    protected abstract void assertFalse(boolean conditions,String message );
    protected abstract void assertEquals(String actual,String expected,String message );

    public void Equals(String actual,String expected,String message){
        assertEquals(actual,expected,message);
    }

    public void isElementVisable(By locator){
        boolean flag=waitManager.fluentWait().until(d->
        {
            try {
                driver.findElement(locator).isDisplayed();
                return true;
            }catch (Exception e){
                return false;
            }
        });
        assertTrue(flag,"Element with locator: "+locator);
    }

    //verify page url
    public void assertPageUrl(String expectedUrl){
        String actualUrl=driver.getCurrentUrl();
        assertEquals(actualUrl,expectedUrl,"Page URL is not as expected");
    }

    //verify page title
    public void assertPageTitle(String expectedTitle){
        String actualTitle=driver.getTitle();
        assertEquals(actualTitle,expectedTitle,"Page title is not as expected");
    }
}
