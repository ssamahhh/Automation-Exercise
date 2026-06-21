package com.Ecommercee.Pages;

import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final GUIDriver driver;

    public LoginPage(GUIDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By signUpLoginButton = By.cssSelector("a[href='/login']");
    private final By loginEmailLocator = By.cssSelector("[data-qa='login-email']");
    private final By loginPasswordLocator = By.cssSelector("[data-qa='login-password']");
    private final By loginButton = By.cssSelector("[data-qa='login-button']");
    private final By loggedInUser = By.xpath("//a[contains(.,'Logged in as')]");
    private final By loginErrorMsg=  By.xpath("//form[@action='/login']//p");
    private final By logoutButton=By.cssSelector("a[href='/logout']");
    private final By cartButtonlocator = By.xpath("//*[normalize-space(text())='Cart']");

    //actions
    public LoginPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+"/login");
        return this;
    }

    public LoginPage clickonSignupLogin()
    {
        driver.element().click(signUpLoginButton);
        return this;
    }

    public LoginPage loginForm(String email,String password){
        driver.element().type(loginEmailLocator,email);
        driver.element().type(loginPasswordLocator,password);
        return this;
    }

    public LoginPage clickLoginButton(){
        driver.element().click(loginButton);
        return this;
    }

    public LoginPage vrefiyLoggedSuccess(String expectedText){
        String actual=driver.element().getText(loggedInUser);
        driver.validate().Equals(actual,expectedText,"");
        return this;
    }

    public LoginPage verifyErrorMsg(String expectedText){
        String actual=driver.element().getText(loginErrorMsg);
        driver.validate().Equals(actual,expectedText,"");
        return this;
    }

    public LoginPage clickLogoutButton(){
        driver.element().click(logoutButton);
        return this;
    }

    @Step("click on cart button")
    public CartPage ClickonCartButton() {
        driver.element().click(cartButtonlocator);
        return new CartPage(driver);
    }
}
