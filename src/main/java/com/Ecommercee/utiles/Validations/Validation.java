package com.Ecommercee.utiles.Validations;

import com.Ecommercee.utiles.logs.logsManager;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

//Soft Assertion
public class Validation extends BaseAssertion {

    private static SoftAssert softAssert=new SoftAssert();
    public static boolean used=false;

    public Validation() {
        super();
    }

    public Validation(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void assertTrue(boolean conditions, String message) {
        used=true;
         softAssert.assertTrue(conditions,message);
    }

    @Override
    protected void assertFalse(boolean conditions, String message) {
        used=true;
        softAssert.assertFalse(conditions,message);
    }

    @Override
    protected void assertEquals(String actual, String expected, String message) {
        used=true;
        softAssert.assertEquals(actual,expected,message);
    }

    public static void assertAll(){
        if(!used) return;
        try{
            softAssert.assertAll();

        } catch (Exception e) {
            logsManager.error("Assertion failed", e.getMessage());
        }finally {
             softAssert=new SoftAssert();
        }
    }
}
