package com.Ecommercee.utiles.Validations;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

//Hard Assertion
public class Verification extends BaseAssertion {

    public Verification() {
        super();
    }
    public Verification(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void assertTrue(boolean conditions, String message) {
        Assert.assertTrue(conditions,message);
    }

    @Override
    protected void assertFalse(boolean conditions, String message) {
        Assert.assertFalse(conditions,message);
    }

    @Override
    protected void assertEquals(String actual, String expected, String message) {
        Assert.assertEquals(actual,expected,message);
    }

}
